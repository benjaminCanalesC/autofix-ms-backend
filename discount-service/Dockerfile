FROM openjdk:17
ARG JAR_FILE=target/discount-service.jar
COPY ${JAR_FILE} discount-service.jar
ENTRYPOINT ["java","-jar","/discount-service.jar"]