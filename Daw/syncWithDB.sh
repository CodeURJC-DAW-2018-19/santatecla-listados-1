#!/bin/sh

while ! mysql --protocol TCP -u "daw_grupo9" -p "URJCDAWGrupo9" -e"show databases;" > /dev/null 2>&1; do
	sleep 3
	echo 'app failed'
done
java -jar target/daw-0.0.1-SNAPSHOT.jar
