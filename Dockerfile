FROM openjdk:14
EXPOSE 8080
ADD target/spring-crud-api.jar spring-crud-api.jar
ENTRYPOINT ["java","-jar","/spring-crud-api.jar"]