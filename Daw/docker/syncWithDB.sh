#!/bin/sh

echo "Ready..."
until nc -z -v -w30 daw_grupo9_1 3306
do
  echo "Steady..."
  sleep 9
done
    echo "Go!"
java -jar target/daw-0.0.1-SNAPSHOT.jar
s
