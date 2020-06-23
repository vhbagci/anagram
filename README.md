# Getting Started

### How to build & run application

Application requires Java 11 JRE. Be sure to set JAVA_HOME path right.

#### Build application

```
mvnw package
```

To build without running tests

```
mvnw package -DskipTests
```

#### Run application with embedded server

Default port is 8080. You may change application.yml setting server.port for a different value.

```
mvnw spring-boot:run
```

Endpoint: http://localhost:8080/anagrams

### How to run tests

```
mvnw test
```

### How to build the Docker image

```
mvnw package -Ddockerfile.skip=false -DskipTests
```

Running the image in a container called anagram with a 8080 port biding.
```
docker run -p 8080:8080 --name anagram volkan/anagram-service:latest
 
```

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)