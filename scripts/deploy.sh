#!/bin/bash

# Echo a message indicating the start of deployment
echo "Starting deployment..."

# Compile the project
# javac -d bin -sourcepath src -cp lib/* src/main/java/ca/concordia/server/MainServer.java

# Package the project (if applicable, e.g., into a JAR)
# jar cvf ThreadBankServer.jar -C bin .

# Transfer the package to a deployment environment/server (if applicable)
# scp ThreadBankServer.jar user@deployment_server:/path/to/deployment

# Or run the project locally
# java -cp bin:lib/* ca.concordia.server.MainServer

# Echo a message indicating the end of deployment
echo "Deployment complete."

