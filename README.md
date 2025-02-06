# Getting Started

## Overview
This project demonstrates a Spring Boot application with PostgreSQL as the database. It supports both read and write operations and integrates with Prometheus for metrics collection. Additionally, the application can be containerized using Docker.

## Features
- **Spring Boot with PostgreSQL**: Provides a structured way to interact with relational databases.
- **Read and Write Operations**: Optimized for efficient data access.
- **Prometheus Metrics**: Collects and exposes application metrics for monitoring.
- **Docker Support**: Easily deployable with Docker Hub integration.

## Prometheus Integration
The application is configured to expose metrics in a Prometheus-compatible format. These metrics help in monitoring database connections, query performance, and overall application health.

## Docker Hub Repository
The application is available on Docker Hub:
[Docker Hub - Demo App](https://hub.docker.com/repository/docker/emreatalay22/demo-app/general)

## Reference Documentation
For further reference, please consider the following sections:

- [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
- [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.2/maven-plugin)
- [Create an OCI image](https://docs.spring.io/spring-boot/3.4.2/maven-plugin/build-image.html)
- [Spring Boot Testcontainers support](https://docs.spring.io/spring-boot/3.4.2/reference/testing/testcontainers.html#testing.testcontainers)
- [Testcontainers Postgres Module Reference Guide](https://java.testcontainers.org/modules/databases/postgres/)
- [Spring Data JDBC](https://docs.spring.io/spring-boot/3.4.2/reference/data/sql.html#data.sql.jdbc)
- [Spring Data JPA](https://docs.spring.io/spring-boot/3.4.2/reference/data/sql.html#data.sql.jpa-and-spring-data)
- [JDBC API](https://docs.spring.io/spring-boot/3.4.2/reference/data/sql.html)
- [Spring REST Docs](https://docs.spring.io/spring-restdocs/docs/current/reference/htmlsingle/)
- [Testcontainers](https://java.testcontainers.org/)
- [Spring Web](https://docs.spring.io/spring-boot/3.4.2/reference/web/servlet.html)
- [Spring Web Services](https://docs.spring.io/spring-boot/3.4.2/reference/io/webservices.html)

## Guides
The following guides illustrate how to use some features concretely:

- [Using Spring Data JDBC](https://github.com/spring-projects/spring-data-examples/tree/master/jdbc/basics)
- [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
- [Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)
- [Managing Transactions](https://spring.io/guides/gs/managing-transactions/)
- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
- [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
- [Producing a SOAP web service](https://spring.io/guides/gs/producing-web-service/)

## Testcontainers Support
This project uses [Testcontainers at development time](https://docs.spring.io/spring-boot/3.4.2/reference/features/dev-services.html#features.dev-services.testcontainers).

Testcontainers has been configured to use the following Docker images:

- [`postgres:latest`](https://hub.docker.com/_/postgres)

Please review the tags of the used images and set them to the same as you're running in production.

## Maven Parent Overrides
Due to Maven's design, elements are inherited from the parent POM to the project POM. While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent. To prevent this, the project POM contains empty overrides for these elements. If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

