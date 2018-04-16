FROM openjdk:8-jre-alpine
COPY ./build/libs/gs-spring-boot-0.1.0.jar /usr/src/
WORKDIR /usr/src/
EXPOSE 8082
CMD ["java", "-jar", "gs-spring-boot-0.1.0.jar"]

