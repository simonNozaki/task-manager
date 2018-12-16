-- task, task_historyのタスクラベルカラムをnullbaleに変更します。

-- Gradleからマイグレーションを実行、gradle flywayMigrate -i

-- table:task
ALTER TABLE task ALTER COLUMN task_label DROP NOT NULL;
-- table:task_history
ALTER TABLE task_history ALTER COLUMN task_label DROP NOT NULL;