FROM openjdk:17
ARG JAR_FILE=target/summary1-service.jar
COPY ${JAR_FILE} summary1-service.jar
ENTRYPOINT ["java","-jar","/summary1-service.jar"]