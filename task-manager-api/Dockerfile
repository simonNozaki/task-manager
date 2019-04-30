FROM tomcat:8.5-jre8

# tomcat設定ファイルをコピー
COPY ext/tomcat/tomcat-users.xml /usr/local/tomcat/conf/
COPY ext/tomcat/server.xml /usr/local/tomcat/conf/

# warファイルを配備する
COPY build/libs/parallel-api.war /usr/local/tomcat/webapps

CMD ["catalina.sh", "run"]
