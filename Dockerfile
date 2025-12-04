# 1) Build Stage (Jar 빌드)
FROM gradle:8.14-jdk21-alpine AS builder
WORKDIR /app
COPY . .
RUN gradle clean bootJar --no-daemon

# 2) Run Stage
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
