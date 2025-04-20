#!/bin/bash

source ../.env

# Create the database table.
## Drops the entire table if it already exists.
mysql --user="$DB_USER" --password="$DB_PASS" --database="$DB_NAME" --execute="DROP DATABASE $DB_NAME; CREATE DATABASE $DB_NAME;"

# Import all of the necessary sql files and data into the database.
for filename in sql/*.sql; do
    [ -e "$filename" ] || continue

    mysql --user="$DB_USER" --password="$DB_PASS" --database="$DB_NAME" < "./$filename"
done
