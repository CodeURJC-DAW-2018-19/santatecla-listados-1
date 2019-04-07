#!/bin/sh
echo "Empezamos"
cd ..
echo "ng"
cd ng
echo "node"
docker run --rm --name angular-cli -v ${PWD}:/angular -w /angular node:8.15.1 /bin/bash -c "npm install -g @angular/cli; ng config -g cli.warnings.versionMismatch false; npm install; ng build --prod --baseHref=/new/"
echo "copy"
cp -R dist/my-app/ ../src/main/resources/static/new
echo "cd"
cd ..
echo "run"
docker run --rm -v "$PWD":/usr/src/project -w /usr/src/project maven:alpine mvn -DskipTests package
cp target/daw-0.0.1-SNAPSHOT.jar docker/
cd docker
docker build -t daw .
