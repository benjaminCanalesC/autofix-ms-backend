FROM openjdk:17
ARG JAR_FILE=target/summary2-service.jar
COPY ${JAR_FILE} summary2-service.jar
ENTRYPOINT ["java","-jar","/summary2-service.jar"]