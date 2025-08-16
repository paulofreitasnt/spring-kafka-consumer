FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/*.jar app.jar
RUN apk add --no-cache curl
COPY wait-for-kafka.sh /wait-for-kafka.sh
RUN chmod +x /wait-for-kafka.sh
ENTRYPOINT ["/wait-for-kafka.sh", "kafka:9092", "--", "java", "-jar", "app.jar"]