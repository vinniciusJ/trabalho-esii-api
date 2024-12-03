# Para buildar dockerfile: docker build -t trabalho-esii-api .
# Para rodar aplicação: docker run -p 8080:8080 trabalho-esii-api

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/project-esii-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
