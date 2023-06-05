Test task
=======================
The application starts a scheduled task that downloads files from https://loremflickr.com and saves them to the database.

Application tests use testcontainers, so you need launched docker for build, or you could skip tests

For run application on local system use DEV app profile and override properties if it needed.
[application-dev.properties](src/main/resources/application-dev.properties)

For run application in docker environment use [compose.yaml](compose.yaml)

Logs file - ./build/app.log