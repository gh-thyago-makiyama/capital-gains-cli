#!/bin/bash

JAR_NAME="capitalgainscli-1.0.jar"
JAR_PATH="./build/libs/$JAR_NAME"
IMAGE_NAME="capital-gains-app"

USE_DOCKER=false

# ANSI Colors
RESET="\033[0m"
BOLD="\033[1m"
GREEN="\033[32m"
RED="\033[31m"
CYAN="\033[36m"

# Detect --docker flag
if [[ "$1" == "--docker" ]]; then
  USE_DOCKER=true
  shift
fi

# Read input (supports < input.txt)
INPUT=$(cat)

if $USE_DOCKER; then
  if [[ "$(docker images -q $IMAGE_NAME 2> /dev/null)" == "" ]]; then
    echo -e "${CYAN}Building Docker image...${RESET}"
    docker build -t $IMAGE_NAME -f Dockerfile . > /dev/null 2>&1 || {
      echo -e "${RED}Docker build failed.${RESET}"
      exit 1
    }
    echo -e "${GREEN}Docker image built successfully.${RESET}"
  fi

  echo -e "${CYAN}Capital Gains CLI${RESET}"
  echo "$INPUT" | docker run --rm -i $IMAGE_NAME

else
  if [ ! -f "$JAR_PATH" ]; then
    echo -e "${CYAN}Building Kotlin application...${RESET}"
    ./gradlew clean shadowJar --quiet > /dev/null 2>&1 || {
      echo -e "${RED}Build failed.${RESET}"
      exit 1
    }
    echo -e "${GREEN}Build completed successfully.${RESET}"
  fi

  echo -e "${CYAN}Capital Gains CLI${RESET}"
  echo "$INPUT" | java -jar "$JAR_PATH"
fi