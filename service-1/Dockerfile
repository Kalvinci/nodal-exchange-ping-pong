# Use an official Java 8 runtime as a parent image
FROM openjdk:8-jdk-alpine

# Install curl
RUN apk add --no-cache curl

# Copy the jar file into the container
COPY target/service-1-1.0.0.jar service-1-1.0.0.jar

# Run the command to start the service when the container launches
CMD ["java", "-jar", "service-1-1.0.0.jar"]