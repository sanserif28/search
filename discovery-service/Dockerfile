FROM openjdk:11-jdk
VOLUME /discovery-service
COPY build/libs/*.jar discovery-service.jar
ENTRYPOINT ["java","-jar","/discovery-service.jar"]