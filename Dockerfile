FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/Technical-Test-0.0.1.jar
COPY ${JAR_FILE} app_testsupermarket.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_testsupermarket.jar"]