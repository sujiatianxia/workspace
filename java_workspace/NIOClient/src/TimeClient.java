import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2015/8/12.
 */
public class TimeClient {
    public static void main(String[] args){
        new Thread(new TimeClientHandle("127.0.0.1", 7777), "TimeClient-001").start();
    }

    private static class TimeClientHandle implements Runnable {
        private String host;
        private int port;
        private Selector selector;
        private SocketChannel socketChannel;
        private volatile boolean stop;

        public TimeClientHandle(String host, int port) {
            this.host = host == null?"127.0.0.1":host;
            this.port = port;

            try {
                selector = Selector.open();
                socketChannel = SocketChannel.open();
                socketChannel.configureBlocking(false);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        @Override
        public void run() {
            try{
                doConnect();
            }catch (IOException e){
                e.printStackTrace();
                System.exit(1);
            }

            while (!stop){
                try {
                    selector.select(1000);
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    SelectionKey key = null;
                    while (iterator.hasNext()){
                        key = iterator.next();
                        iterator.remove();
                        try{
                            handleInput(key);
                        }catch (Exception e){
                            if (key != null){
                                key.cancel();
                                if (key.channel() != null){
                                    key.channel().close();
                                }
                            }
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }

            //��·�������رպ�����ע���������Channel��Pipe����Դ���ᱻ�Զ�ȥע��Ͳ��رգ����Բ���Ҫ�ظ��ͷ���Դ
            if (selector != null){
                try{
                    selector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void handleInput(SelectionKey key) throws IOException {
            if (key.isValid()){
                //�ж��Ƿ����ӳɹ�
                SocketChannel sc = (SocketChannel) key.channel();
                if (key.isConnectable()){
                    if (sc.finishConnect()){
                        sc.register(selector, SelectionKey.OP_READ);
                        doWrite(sc);
                    }else{
                        System.exit(1);//����ʧ�ܣ������˳�
                    }

                    if (key.isReadable()){
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        int readBytes = sc.read(readBuffer);
                        if (readBytes > 0){
                            readBuffer.flip();
                            byte[] bytes = new byte[readBuffer.remaining()];
                            readBuffer.get(bytes);
                            String body = new String(bytes, "UTF-8");
                            System.out.println("Now is:" + body);
                            this.stop = true;
                        }else if (readBytes < 0){
                            //�Զ���·�ر�
                            key.cancel();
                            sc.close();
                        }else{
                            ;//����0�ֽڣ�����
                        }
                    }
                }
            }
        }

        private void doWrite(SocketChannel sc) throws IOException {
            byte[] req = "QUERY TIME ORDER".getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
            writeBuffer.put(req);
            writeBuffer.flip();
            sc.write(writeBuffer);
            if (!writeBuffer.hasRemaining()){
                System.out.println("Send order 2 server succeed.");
            }
        }

        private void doConnect() throws IOException {
            //���ֱ�����ӳɹ�����ע�ᵽ��·�������ϣ�����������Ϣ����Ӧ��
            if (socketChannel.connect(new InetSocketAddress(host, port))){
                socketChannel.register(selector, SelectionKey.OP_READ);
                doWrite(socketChannel);
            }else {
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
            }
        }
    }
}