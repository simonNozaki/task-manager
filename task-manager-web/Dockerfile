# ===============Commands===============
# docker build -t task-manager-web:1.0.0 .
# docker run --name task-manager-web -d -p 13000:3000 task-manager-web:1.0.0 
# docker ps
# docker ps -a
# docker container stop task-maanager-web
#
# docker-compose up -d
# docker-compose ps
# docker-compose down
# =====================================

# CentOS7
FROM centos:centos7

# nodejsの関連モジュールをインストールします.
RUN yum install -y epel-release && \
    yum install -y nodejs npm && \
    mkdir -p /app/task-manager-web

# http, https用のポートをそれぞれ解放しておきます.
EXPOSE 3000

# ソースコードをコピーします.
COPY . /app/task-manager-web

# nodeモジュール, angularをまとめてインストールします.
RUN cd /app/task-manager-web && npm install && ls -la && npm install -g @angular/cli

# 実行ディレクトリを移動しておきます.
WORKDIR /app/task-manager-web

# angularアプリを起動します.
CMD ng serve --open