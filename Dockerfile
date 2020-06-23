ARG IMAGE_VERSION=alpine-jre
FROM adoptopenjdk/openjdk11-openj9:$IMAGE_VERSION
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]