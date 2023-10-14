FROM openjdk:17
VOLUME /tmp
WORKDIR /app
#ARG JAR_FILE=build/libs/LinkShortener-V1.0.jar
#COPY ${JAR_FILE} app.jar
#ADD build/libs/LinkShortener-V1.0.jar app.jar
COPY build/libs/LinkShortener-V1.0.jar app.jar
EXPOSE 9999
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]
#ENTRYPOINT ["java -Dspring.profiles.active=prod -jar /app.jar"]

#to build
#docker build -t linkshortener:1.0 .