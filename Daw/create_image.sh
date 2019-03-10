#!/bin/sh
docker run --rm -v "$PWD":/usr/src/project -w /usr/src/project maven:alpine mvn -DskipTests package
docker login --username anaalvarezzz11 --password URJCDAWGrupo9
docker build -t anaalvarezzz11/daw .
docker push anaalvarezzz11/daw:latest
