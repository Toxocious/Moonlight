#!/bin/bash

# Create the database table using root credentials (not regular user)
## Drops the entire table if it already exists.
echo "Creating Moonlight's database."
mysql -h "$MYSQL_HOST" -P "$MYSQL_PORT" -u "$MYSQL_ROOT_USER" -p"$MYSQL_ROOT_PASSWORD" --execute="DROP DATABASE IF EXISTS $MYSQL_NAME; CREATE DATABASE $MYSQL_NAME;"
echo "Database created."

# Grant privileges to the application user
mysql -h "$MYSQL_HOST" -P "$MYSQL_PORT" -u "$MYSQL_ROOT_USER" -p"$MYSQL_ROOT_PASSWORD" --execute="GRANT ALL PRIVILEGES ON $MYSQL_NAME.* TO '$MYSQL_USER'@'%'; FLUSH PRIVILEGES;"
echo "User permissions granted."

# Import all of the necessary sql files and data into the database.
echo "Importing SQL files into the database."
for filename in sql/*.sql; do
    [ -e "$filename" ] || continue
    echo "Importing $filename"

    mysql -h "$MYSQL_HOST" -P "$MYSQL_PORT" -u "$MYSQL_USER" -p"$MYSQL_PASSWORD" --database="$MYSQL_NAME" < "$filename"
    echo "Import completed."
done
echo "All SQL files have been imported into the database."
