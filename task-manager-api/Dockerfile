# ===============Commands===============
# docker build -t task-manager-api:1.0.0 .
# docker run --name task-manager-api -d -p 18080:8080 task-manager-api:1.0.0
# docker ps
# docker container stop task-manager-api
# docker-compose up -d
# docker-compose ps
# docker-compose down
#
# curl -g http://localhost:28080/api/v1/task/fetch?userId=10001000
# =====================================
FROM centos:7

# 地域の設定
RUN rm -f /etc/rpm/macros.image-language-conf && \
    sed -i '/^override_install_langs=/d' /etc/yum.conf && \
    yum -y install glibc-common && \
    yum clean all && \
	rm -rf /var/cache/yum && \
	localedef -f UTF-8 -i ja_JP ja_JP.UTF-8

# タイムゾーンを設定します.
RUN unlink /etc/localtime && \
	ln -s /usr/share/zoneinfo/Japan /etc/localtime

# 環境変数を設定します.
ENV LANG=ja_JP.UTF-8
ENV LANGUAGE=ja_JP.UTF-8
ENV LC_ALL=ja_JP.UTF-8
ENV APP_NAME=task-manager-api
# アプリのRoot Directory
ENV APP_HOME=/src/bin
# JDK
ENV JAVA_HOME=$APP_HOME/jdk1.8.0_152
# Tomcat系
ENV TOMCAT_HOME=$APP_HOME/apache-tomcat-8.5.29
ENV CATALINA_HOME=$APP_HOME/apache-tomcat-8.5.29
ENV PATH=$PATH:$APP_HOME/apache-tomcat-8.5.29/bin:$JAVA_HOME/bin
ENV NLS_LANG=JAPANESE_JAPAN.JA16SJISTILDE
ENV TZ=JST-9

RUN mkdir -p $APP_HOME
# ローカルのJDK、Tomcat、warファイルをコピーしておき、コンテナで解凍します.
ADD docker/midware/jdk-8u152-linux-x64.tar.gz $APP_HOME
ADD docker/midware/apache-tomcat-8.5.29.tar.gz $APP_HOME
COPY build/libs/task-manager-api-1.0.0.war $TOMCAT_HOME/webapps
COPY docker/tomcat-conf/tomcat-users.xml $TOMCAT_HOME/conf/tomcat-users.xml
VOLUME $TOMCAT_HOME/logs
VOLUME /logs

# コンテナのリッスンするポート番号
EXPOSE 18080
CMD $TOMCAT_HOME/bin/catalina.sh run