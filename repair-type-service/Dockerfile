FROM openjdk:17
ARG JAR_FILE=target/repair-type-service.jar
COPY ${JAR_FILE} repair-type-service.jar
ENTRYPOINT ["java","-jar","/repair-type-service.jar"]