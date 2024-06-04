FROM openjdk:17
ARG JAR_FILE=target/surcharge-service.jar
COPY ${JAR_FILE} surcharge-service.jar
ENTRYPOINT ["java","-jar","/surcharge-service.jar"]