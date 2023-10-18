FROM openjdk:17
VOLUME /tmp
WORKDIR /app
COPY build/libs/LinkShortener-V1.0.jar app.jar
ENV PROD_DB_LINK=jdbc:postgresql://shortlink-1.cjfbwpo4wfwq.eu-north-1.rds.amazonaws.com:5432/shortlink_db
ENV PROD_DB_USERNAME=userprod
ENV PROD_DB_PASSWORD=postgrespass
ENV SECURITY_USER=user
ENV SECURITY_PASS=default
ENV PORT=9999
ENV APP_VERSION=v.1.0
ENV TOKEN_LENGTH=5
ENV VALIDITY_PERIOD=72
ENV CACHE_SIZE=1000
ENV CACHE_PERIOD_MINUTES=15
EXPOSE 9999
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]


#to build
#docker build -t <имя>/<репозиторий/имя проекта>:версия .
#docker build -t t2/linkshortener:1.0 .
#run
#docker run <container-id>
#docker run -d -p 9999:9999 -t t2/linkshortener:1.0