FROM eclipse-temurin:17-jdk

# Install required packages
RUN apt-get update && \
    apt-get install -y maven git mysql-client && \
    apt-get clean

# Copy the entire project
COPY . /app/

# Set working directory to the Server directory where pom.xml is located
WORKDIR /app/Server

# Build the application
RUN mvn clean package

# Copy the init database script
COPY Server/init_database.sh /app/Server
RUN chmod +x /app/Server/init_database.sh

# Copy the entrypoint script
COPY Server/docker-entrypoint.sh /app/Server
RUN chmod +x /app/Server/docker-entrypoint.sh

ENTRYPOINT ["/app/Server/docker-entrypoint.sh"]
