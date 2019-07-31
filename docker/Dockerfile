FROM openjdk:13-alpine as builder
COPY build/libs/hello-0.0.1-SNAPSHOT.jar /app.jar
RUN java -Xshare:dump -XX:SharedArchiveFile=app-cds.jsa --class-path app.jar

FROM openjdk:13-alpine
WORKDIR /app/
EXPOSE 8080
COPY --from=builder /app-cds.jsa .
COPY --from=builder /app.jar .
CMD java -Xshare:on -XX:SharedArchiveFile=app-cds.jsa -jar app.jar