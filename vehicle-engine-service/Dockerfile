FROM openjdk:17
ARG JAR_FILE=target/vehicle-engine-service.jar
COPY ${JAR_FILE} vehicle-engine-service.jar
ENTRYPOINT ["java","-jar","/vehicle-engine-service.jar"]