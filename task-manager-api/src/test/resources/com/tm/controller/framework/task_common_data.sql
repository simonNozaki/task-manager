-- タスク処理Controller共通データソースFile

-- Users、1ユーザデフォルトで作成
-- pass : stubstub
INSERT INTO public.users(user_id, user_name, email, password, used_flag)
    VALUES ('TM00000001', 'test user', 'test@example.com', '$2a$10$x7HcJvFPjTkpxSvR0upo0e0RpmHDNQt1EM4Ybfsr/erGRC9JfkYqO', '0');

-- task
INSERT INTO public.task(task_id, task_title, task_label, start_date, deadline, completed_flag, user_id, task_note)
    VALUES
    ('180101000a', 'sample title1', 'test label1', '2018-01-01 00:00:00', '2018-01-03 00:00:00', '0', 'TM00000001', 'test note'),
    ('180101000b', 'sample title2', 'test label2', '2018-01-01 00:00:00', '2018-01-03 00:00:00', '0', 'TM00000001', 'test note'),
    ('180101000c', 'sample title3', 'test label3', '2018-01-01 00:00:00', '2018-01-03 00:00:00', '0', 'TM00000001', 'test note');

-- task_label
INSERT INTO public.task_label(label_id, task_label, used_flag, user_id)
    VALUES
    ('TL00000001', 'test label1', '0', 'TM00000001'),
    ('TL00000002', 'test label2', '0', 'TM00000001'),
    ('TL00000003', 'test label3', '0', 'TM00000001');

