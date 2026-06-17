FROM eclipse-temurin:21-jre

COPY target/springboot-demo-app-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]