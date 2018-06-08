FROM openjdk:8-jre-alpine
COPY build/libs/gs-spring-boot-0.1.0.jar /
COPY newrelic/newrelic.jar /
COPY newrelic/newrelic.yml /
EXPOSE 8081

CMD java -javaagent:newrelic.jar -jar -Dserver.port=8081 -Dspring.profiles.active=prod gs-spring-boot-0.1.0.jar
