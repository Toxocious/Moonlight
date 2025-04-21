#!/bin/bash

# CLI flags
no_cache=false

# Parse command-line flags
while getopts "c" flag; do
  case $flag in
    c) no_cache=true ;;
    *) exit 1 ;;
  esac
done

# For more verbose Docker build logs
# export BUILDKIT_PROGRESS=plain

# Build with no cache to ensure fresh build
echo "Building Docker Containers"
if [ "$no_cache" = true ]; then
    docker-compose build --no-cache
else
    docker-compose build
fi

# Run in foreground with all logs visible
echo "Starting containers..."
docker-compose up --no-build

