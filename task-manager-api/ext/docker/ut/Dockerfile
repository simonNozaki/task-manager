# UT用DBDockerコンテナ定義
# ===============Commands===============
# docker build -t ut-pg-task-base:1.0.0 .
# docker run --name ut-pg-task-base -d -p 15532:5432 ut-pg-task-base:1.0.0
# docker ps
# docker ps -a
# docker container stop ut-pg-task-base
# =====================================
FROM postgres:9.6.10

# 初期ユーザの設定
ENV POSTGRES_USER=taskmanageruser
ENV POSTGRES_PASSWORD=taskmanageruser
ENV POSTGRES_DB=ut_task_base

# 待受ポート設定
EXPOSE 5432
