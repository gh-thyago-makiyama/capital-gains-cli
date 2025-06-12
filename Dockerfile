# ---------- BUILD STAGE ----------
FROM gradle:8.10.0-jdk21-alpine AS builder

WORKDIR /app
COPY . .

# Cache-friendly: only reruns shadowJar if the code changes
RUN gradle shadowJar --no-daemon

# ---------- RUNTIME STAGE ----------
FROM amazoncorretto:21-alpine3.18

WORKDIR /app
COPY --from=builder /app/build/libs/capitalgainscli-1.0.jar app.jar

# Runs the CLI application with redirected input
ENTRYPOINT ["java", "-jar", "/app/app.jar"]