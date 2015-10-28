alter table chat.user_group drop foreign key FKjonf4pqux3h1e687sd18dhcnj;
alter table chat.user_group drop foreign key FK1c1dsw3q36679vaiqwvtv36a6;
drop table if exists chat.group;
drop table if exists chat.user;
drop table if exists user_group;
create table chat.group (id varchar(36) not null, name varchar(16), primary key (id));
create table chat.user (id varchar(36) not null, nickname varchar(16), password varchar(16), primary key (id));
create table chat.user_group (user_id varchar(36), group_id varchar(36) not null, primary key (user_id, group_id));
alter table chat.user_group add constraint FKjonf4pqux3h1e687sd18dhcnj foreign key (group_id) references chat.group (id);
alter table chat.user_group add constraint FK1c1dsw3q36679vaiqwvtv36a6 foreign key (user_id) references chat.user (id);