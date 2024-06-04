FROM openjdk:17
ARG JAR_FILE=target/vehicle-brand-service.jar
COPY ${JAR_FILE} vehicle-brand-service.jar
ENTRYPOINT ["java","-jar","/vehicle-brand-service.jar"]