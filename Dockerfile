FROM gradle:7.6-jdk17-alpine AS builder
USER root
WORKDIR /builder
ADD . /builder
RUN gradle build --stacktrace

FROM openjdk:17-alpine3.12
WORKDIR /app
EXPOSE 8080
COPY --from=builder /builder/build/libs/vtrci-0.0.1-SNAPSHOT.jar server.jar
CMD ["java", "-jar", "server.jar"]
