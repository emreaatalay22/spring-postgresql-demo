FROM maven:3.8.6-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
# Bağımlılıkları önceden yükle (cache için)
RUN mvn dependency:go-offline -Pdocker

COPY src ./src
# Profil belirterek build
RUN mvn clean install -Pdocker

# Base image olarak openjdk kullanıyoruz.
FROM openjdk:23-jdk-slim

# Build arg ile ortam değişkeni alıyoruz
ENV ACTIVE_PROFILE=docker
ENV SPRING_PROFILES_ACTIVE=docker

COPY ./target/demo-0.0.1-SNAPSHOT.jar /usr/app/demo.jar

WORKDIR /usr/app

EXPOSE 8080

# Uygulamanın çalıştırılması için Java komutunu veriyoruz
CMD ["java", "-jar", "demo.jar"]
