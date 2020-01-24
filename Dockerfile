FROM openjdk:8
ADD /target/spring-boot-rest-crud-0.0.6-SNAPSHOT.jar spring-boot-rest-crud.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-boot-rest-crud.jar"]
