-- 利用者情報の一意性を、メールアドレスおよびパスワードにて担保できるようにします。

-- Gradleからマイグレーションを実行、gradle flywayMigrate -i

-- 変更対象を外部キーとしている制約を一回解除する
ALTER TABLE public.task DROP CONSTRAINT task_user_id_fkey;
ALTER TABLE public.task_history DROP CONSTRAINT task_history_user_id_fkey;

-- 利用者テーブルの制約を一回外す
ALTER TABLE users DROP CONSTRAINT users_pkey;

-- 新しいキーを設定する
ALTER TABLE users ADD CONSTRAINT users_pkey PRIMARY KEY(user_id, email, password);

-- 外部キー制約を元に戻す
ALTER TABLE public.task ADD CONSTRAINT task_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES public.users (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE public.task_history ADD CONSTRAINT task_history_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES public.users (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;