FROM openjdk:17
ARG JAR_FILE=target/vehicle-type-service.jar
COPY ${JAR_FILE} vehicle-type-service.jar
ENTRYPOINT ["java","-jar","/vehicle-type-service.jar"]