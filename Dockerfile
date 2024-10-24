# Eclipse Temurin의 OpenJDK 17을 기반으로 설정
FROM eclipse-temurin:17-jdk-alpine

# build가 되는 시점에 JAR_FILE이라는 변수 명에 build/libs/*.jar 선언
ARG JAR_FILE=build/libs/*.jar

# JAR_FILE을 app.jar로 복사
COPY ${JAR_FILE} app.jar

# 운영 환경에서 prod 프로파일을 활성화하여 실행
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/app.jar"]
