FROM openjdk:17-alpine
COPY users-service/build/libs/users-service-1.0.0.jar users-service.jar
ENV SPRING_DATASOURCE_URL="jdbc:postgresql://localhost:5432/users"
ENV SPRING_DATASOURCE_URERNAME="app_user"
ENV SPRING_DATASOURCE_PASSWORD="Postgres123"
EXPOSE 8081
ENTRYPOINT ["java","-jar","/users-service.jar"]
