# 開発用DBコンテナ定義
# ===============Commands===============
# docker build -t pg-task-base:1.0.0 .
# docker run --name pg-task-base -d -p 5532:5432 pg-task-base:1.0.0
# docker ps
# docker ps -a
# docker container stop pg-task-base
# =====================================
FROM postgres:9.6.10

# 初期ユーザの設定
ENV POSTGRES_USER=taskmanageruser
ENV POSTGRES_PASSWORD=taskmanageruser
ENV POSTGRES_DB=task_base

# 待受ポート設定
EXPOSE 5432
