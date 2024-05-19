# Sử dụng image chứa JDK 17
FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
# Sao chép file JAR của ứng dụng Spring Boot vào thư mục app
COPY ./target/studentsystem-0.0.1-SNAPSHOT.jar app.jar
# Chạy ứng dụng Spring Boot
ENTRYPOINT ["java", "-jar", "/app.jar"]