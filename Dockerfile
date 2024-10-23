FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
VOLUME /tmp

# Copy the Spring Boot JAR file from the target directory into the container
COPY target/UserService-0.0.1-SNAPSHOT.jar /app/UserService.jar

# Expose the port the application will run on
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "/app/UserService.jar"]
