# Stage 1: Build with Maven and JDK 21
FROM maven:3.8.7 as build

# Install OpenJDK 21
RUN apt-get update && \
    apt-get install -y openjdk-21-jdk

# Set the working directory
WORKDIR /app

# Copy the pom.xml and the source code
COPY pom.xml .
COPY src ./src

# Set the JAVA_HOME to JDK 21
ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64

# Run Maven build (without tests for faster builds)
RUN mvn -B clean package -DskipTests

# Stage 2: Run with OpenJDK 21
FROM openjdk:21

# Set the working directory
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build ./target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
