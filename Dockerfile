FROM openjdk:17
VOLUME /tmp
WORKDIR /app
#ARG JAR_FILE=build/libs/LinkShortener-V1.0.jar
#COPY ${JAR_FILE} app.jar
#ADD build/libs/LinkShortener-V1.0.jar app.jar
COPY build/libs/LinkShortener-V1.0.jar app.jar
ENV PROD_DB_LINK=jdbc:postgresql://shortlink-1.cjfbwpo4wfwq.eu-north-1.rds.amazonaws.com:5432/shortlink_db;
ENV PROD_DB_USERNAME=userprod;
ENV PROD_DB_PASSWORD=postgrespass;
ENV SECURITY_USER=user;
ENV SECURITY_PASS=default;
EXPOSE 9999
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]


#to build
#docker build -t linkshortener:1.0 .
#run
#docker run -d -p 8080:9999 -t linkshortener:1.0