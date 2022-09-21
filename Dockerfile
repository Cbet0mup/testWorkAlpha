FROM amazoncorretto:18-alpine-jdk
MAINTAINER pavel.lyutov
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]