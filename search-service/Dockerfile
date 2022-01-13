FROM openjdk:11-jdk
VOLUME /search-service
COPY build/libs/*.jar search-service.jar
ENTRYPOINT ["java","-jar","/search-service.jar"]