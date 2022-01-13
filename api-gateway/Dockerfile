FROM openjdk:11-jdk
VOLUME /api-gateway
COPY build/libs/*.jar api-gateway.jar
ENTRYPOINT ["java","-jar","/api-gateway.jar"]