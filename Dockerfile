FROM amazoncorretto:11-alpine-jdk
#FROM eclipse-temurin:11
COPY target/module4_spring_without_boot-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]