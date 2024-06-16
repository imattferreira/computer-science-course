FROM maven:3.9.6-sapmachine-17
WORKDIR /app
COPY . .
RUN mvn verify clean --fail-never
RUN mvn package
ENTRYPOINT ["java", "-jar", "./target/docker-crud-torrex-spring-0.0.1-SNAPSHOT.jar"]