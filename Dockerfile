FROM openjdk:17
WORKDIR /app
#ADD build/libs/LinkShortener-V1.0.jar app.jar
COPY build/libs/LinkShortener-V1.0.jar app.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]