FROM adoptopenjdk/openjdk11

EXPOSE 8081

WORKDIR /app

ADD build/libs/conditional-0.0.1-SNAPSHOT.jar myapp.jar

ENTRYPOINT "java" "-jar" "myapp.jar"