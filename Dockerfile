FROM openjdk:8-jre-alpine
COPY build/libs/gs-spring-boot-0.1.0.jar /
COPY newrelic/newrelic.jar /
COPY newrelic/newrelic.yml /
EXPOSE 8080
EXPOSE 9010

CMD java -javaagent:newrelic.jar -jar -Dserver.port=8080 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=9010 -Dcom.sun.management.jmxremote.rmi.port=9010 -Dcom.sun.management.jmxremote.local.only=false -Dcom.sun.management.jmxremote.authenticate=false -Djava.rmi.server.hostname=10.0.75.1 -Dcom.sun.management.jmxremote.ssl=false -Dspring.profiles.active=prod gs-spring-boot-0.1.0.jar 