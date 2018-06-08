FROM openjdk:8-jre-alpine
COPY build/libs/gs-spring-boot-0.1.0.jar /
EXPOSE 8081
CMD java -jar -Dserver.port=8081 -Dspring.profiles.active=prod gs-spring-boot-0.1.0.jar
