FROM openjdk:17
ARG JAR_FILE=target/vehicle-service.jar
COPY ${JAR_FILE} vehicle-service.jar
ENTRYPOINT ["java","-jar","/vehicle-service.jar"]