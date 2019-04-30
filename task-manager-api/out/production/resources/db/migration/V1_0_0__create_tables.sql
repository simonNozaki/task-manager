-- DB操作ユーザを作成
--CREATE ROLE localuser WITH LOGIN PASSWORD '21405apple';

-- テーブルを作成
create table  IF NOT EXISTS users(
	user_id CHAR(10) NOT NULL,
	user_name VARCHAR(50) NOT NULL,
	email VARCHAR(100) NOT NULL,
	password VARCHAR(50),
	used_flag CHAR(1) NOT NULL,

	PRIMARY KEY(user_id)
);

create table  IF NOT EXISTS task(
	task_id CHAR(10),
	task_title VARCHAR(100) NOT NULL,
	task_label VARCHAR(50) NOT NULL,
	start_date TIMESTAMP WITH TIME ZONE,
	deadline TIMESTAMP WITH TIME ZONE,
	completed_flag CHAR(1) NOT NULL,
	user_id CHAR(10) NOT NULL,

	PRIMARY KEY(task_id),
	FOREIGN KEY(user_id) REFERENCES users(user_id)
);

create table  IF NOT EXISTS task_history(
	task_id CHAR(10),
	task_title VARCHAR(100) NOT NULL,
	task_label VARCHAR(50) NOT NULL,
	start_date TIMESTAMP WITH TIME ZONE,
	deadline TIMESTAMP WITH TIME ZONE,
	completed_flag CHAR(1) NOT NULL,
	user_id CHAR(10) NOT NULL,

	PRIMARY KEY(task_id),
	FOREIGN KEY(user_id) REFERENCES users(user_id)
);