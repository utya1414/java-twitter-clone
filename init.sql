--------ユーザのマスターテーブル
create table m_user (
user_id varchar(24) primary key,
password varchar(32) not null,
user_name varchar(20) not null unique,
update_datetime timestamp not null default current_timestamp on update current_timestamp
);

--カテゴリのマスターテーブル
create table m_category (
category_id int primary key auto_increment,
category_name varchar(20) not null unique,
update_datetime timestamp not null default current_timestamp on update current_timestamp
);
--ステータスのマスターテーブル
create table m_status (
status_code char(2) primary key,
status_name varchar(20) unique not null,
update_datetime timestamp not null default current_timestamp on update current_timestamp
);
--タスクテーブル
create table t_task (
 task_id int auto_increment primary key,
task_name varchar(50) not null,
category_id int not null,
limit_date date,
user_id varchar(24) not null,
status_code char(2) not null,
memo varchar(100),
create_datetime timestamp default current_timestamp not null,
update_datetime timestamp default current_timestamp not null on update current_timestamp,
foreign key(category_id) references m_category(category_id),
foreign key(user_id) references m_user(user_id),
foreign key(status_code) references m_status(status_code)
);
--コメントテーブル
create table t_comment(
comment_id int auto_increment primary key,
task_id int,
user_id varchar(100) not null,
comment varchar(100) not null,
update_datetime timestamp default current_timestamp not null on update current_timestamp,
foreign key(task_id) references t_task(task_id) on delete set null on update cascade,
foreign key(user_id) references m_user(user_id)
); 
--カテゴリーレコード作成
insert into m_category (category_name) value("新商品A：開発プロジェクト");
insert into m_category (category_name) value("既存商品B：改良プロジェクト");
select * from m_category;
--ステータスレコード作成
insert into m_status (status_code, status_name) values("00", "未着手");
insert into m_status (status_code, status_name) values("50", "着手");
insert into m_status (status_code, status_name) values("99", "完了");
select * from m_status;
--ユーザーレコード作成
insert into m_user(user_id, password, user_name) values("testid", "password", "testuser");
insert into m_user(user_id, password, user_name) values("testid2", "password", "testuser2");
--タスクレコード作成
insert into t_task(task_name, category_id, limit_date, user_id, status_code, memo) values("サンプルタスク1", 1, "2022-10-01", "testid", "99", "テスト入力");
insert into t_task(task_name, category_id, limit_date, user_id, status_code, memo) values("サンプルタスク2", 2, "2020-07-05", "testid", "50", "テスト入力");
insert into t_task(task_name, category_id, limit_date, user_id, status_code, memo) values("サンプルタスク2", 1, "2020-09-30", "testid", "00", "テスト入力");
insert into t_task(task_name, category_id, limit_date, user_id, status_code, memo) values("サンプルタスク3", 2, "2002-08-30", "testid", "99", "テスト入力");
insert into t_task(task_name, category_id, limit_date, user_id, status_code, memo) values("サンプルタスク4", 1, "2000-09-30", "testid", "00", "テスト入力");
insert into t_task(task_name, category_id, limit_date, user_id, status_code, memo) values("サンプルタスク5", 2, "2025-09-30", "testid", "50", "テスト入力");
insert into t_task(task_name, category_id, limit_date, user_id, status_code, memo) values("サンプルタスク6", 1, "1998-09-30", "testid", "00", "テスト入力");
insert into t_task(task_name, category_id, limit_date, user_id, status_code, memo) values("サンプルタスク7", 2, "2020-09-30", "testid", "99", "テスト入力");
insert into t_task(task_name, category_id, limit_date, user_id, status_code, memo) values("サンプルタスク8", 1, "2020-10-30", "testid", "00", "テスト入力");
 