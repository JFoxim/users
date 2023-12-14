FROM openjdk:17-alpine
COPY build/libs/users-0.0.1.jar restservice.jar
ENV SPRING_DATASOURCE_URL="jdbc:postgresql://localhost:5432/users"
ENV SPRING_DATASOURCE_URERNAME="app_user"
ENV SPRING_DATASOURCE_PASSWORD="Postgres123"
EXPOSE 8081
ENTRYPOINT ["java","-jar","/users.jar"]
