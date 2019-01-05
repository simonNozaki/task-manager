-- ハッシュにしたパスワードを許容できるよう、カラムの文字列サイズを変更します。
ALTER TABLE users ALTER COLUMN password TYPE VARCHAR(100);