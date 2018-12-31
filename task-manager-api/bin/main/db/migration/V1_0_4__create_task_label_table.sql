-- 利用者情報の一意性を、メールアドレスおよびパスワードにて担保できるようにします。

-- Gradleからマイグレーションを実行、gradle flywayMigrate -i
-- Mybatisで対応するモデルを生成 gradlew mybatisGenerator

create table  IF NOT EXISTS task_label(
	label_id CHAR(10),
	task_label VARCHAR(50) NOT NULL,
	used_flag CHAR(1) NOT NULL,
	user_id CHAR(10) NOT NULL,

	PRIMARY KEY(label_id),
	FOREIGN KEY(user_id) REFERENCES users(user_id)
);

create table  IF NOT EXISTS task_label_history(
	label_id CHAR(10),
	task_label VARCHAR(50) NOT NULL,
	used_flag CHAR(1) NOT NULL,
	user_id CHAR(10) NOT NULL,

	PRIMARY KEY(label_id),
	FOREIGN KEY(user_id) REFERENCES users(user_id)
);