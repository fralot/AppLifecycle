FROM eclipse-temurin:21-alpine AS builder
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package

FROM eclipse-temurin:21-alpine
VOLUME /tmp
ARG JAR_FILE=target/games-0.0.1-SNAPSHOT.jar
COPY --from=builder /app/${JAR_FILE} app.jar
ENV PROFILE=prd
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]