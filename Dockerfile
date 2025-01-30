# Stage 1: Build with Maven and JDK 21
FROM maven:3.9-openjdk-21 as build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and the source code
COPY pom.xml .
COPY src ./src

# Run Maven build (without tests for faster builds)
RUN mvn -B clean package -DskipTests

# Stage 2: Run with OpenJDK 21
FROM openjdk:21

# Set the working directory
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/Benue_Produce_Logistics_Api.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
