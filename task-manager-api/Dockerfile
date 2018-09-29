# ===============Commands===============
# docker build -t pg-task-base:1.0.0 .
# docker run --name pg-task-base -d -p 5532:5432 pg-task-base:1.0.0
# docker ps
# docker ps -a
# docker container stop pg-task-base
# docker-compose up -d
# docker-compose ps
# docker-compose down
# docker-compose run node-app-service node /app/nodejs/app.js
# =====================================
FROM postgres:9.6.10

# 初期ユーザの設定
ENV POSTGRES_USER=localuser
ENV POSTGRES_PASSWORD=21405apple
ENV POSTGRES_DB=task_base

# データベースをローカルにマウントします
VOLUME /Users/s.nozaki/workspace/data/docker-volume/task-manager-api:/var/lib/postgresql/data

# 待受ポート設定
EXPOSE 5432