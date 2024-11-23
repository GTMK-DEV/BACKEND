FROM eclipse-temurin:17-jdk-alpine

ARG JAR_FILE=build/libs/server-0.0.1-SNAPSHOT.jar

# JAR_FILE을 app.jar로 복사
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
