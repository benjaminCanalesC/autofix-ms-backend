FROM openjdk:17
ARG JAR_FILE=target/brand-discount-service.jar
COPY ${JAR_FILE} brand-discount-service.jar
ENTRYPOINT ["java","-jar","/brand-discount-service.jar"]