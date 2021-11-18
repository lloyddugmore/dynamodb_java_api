FROM amazoncorretto:11-alpine-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN apk update && apk add bash
ENTRYPOINT ["java", "-Djasypt.encryptor.password=${encrypt_key}", "-jar", "/app.jar"]
