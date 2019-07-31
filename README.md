# app-cds

1. gradlew clean assemble
2. docker build -f docker/Dockerfile -t hello .
3. docker run -p 8080:8080 hello
