FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java", \
    "-XX:InitialRAMPercentage=25.0", \
    "-XX:MaxRAMPercentage=75.0", \
    "-jar", "app.jar"]
