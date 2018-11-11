-- タスクテーブルにメモカラムを追加します.
ALTER TABLE task ADD COLUMN task_note VARCHAR(256);

-- タスク履歴テーブルにメモカラムを追加します.
ALTER TABLE task_history ADD COLUMN task_note VARCHAR(256);