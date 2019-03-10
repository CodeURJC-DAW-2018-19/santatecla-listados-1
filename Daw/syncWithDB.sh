#!/bin/sh
until nc -z -v -w30 daw_grupo9 3306
do
  echo "Waiting for database connection..."
  sleep 5
done
java -jar target/daw-0.0.1-SNAPSHOT.jar
