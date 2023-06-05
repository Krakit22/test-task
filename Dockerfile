FROM openjdk:18-alpine
COPY build/libs/*.jar test-task.jar
ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8
ENTRYPOINT java -Dspring.profiles.active=stage -jar test-task.jar
EXPOSE 8080
