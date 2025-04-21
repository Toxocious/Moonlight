#!/bin/bash
set -e

# Wait for MySQL to be ready
# echo "Waiting for MySQL to be ready..."
# until mysql -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" -e "SELECT 1"; do
#   sleep 2
# done

until mysqladmin ping -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_ROOT_USER" -p"$MYSQL_ROOT_PASSWORD" --silent; do
    sleep 1
done

# Execute all SQL migrations
echo "MySQL is ready, initializing database..."
chmod +x /app/Server/init_database.sh && /app/Server/init_database.sh

# Start the server
echo "Starting MapleStory server..."
java -jar bin/maplestory-2.13.1-jar-with-dependencies.jar
