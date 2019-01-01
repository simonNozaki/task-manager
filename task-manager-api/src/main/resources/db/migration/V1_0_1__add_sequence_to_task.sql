-- flywayからgradleのタスクを実行：gradle flywayMigrate -i

-- 各テーブルの所有者を変更します.シーケンスの所有者とテーブルの所有者を一致させます.
ALTER TABLE public.task OWNER TO taskmanageruser;
ALTER TABLE public.task_history OWNER TO taskmanageruser;
ALTER TABLE public.users OWNER TO taskmanageruser;

-- タスクIDに連番を追加
CREATE SEQUENCE task_id_seq OWNED BY task.task_id;

--利用者IDに連番を追加
CREATE SEQUENCE user_id_seq OWNED BY users.user_id;