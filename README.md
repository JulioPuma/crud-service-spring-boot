#  CRUD service using Spring Reactor And H2

<!-- TOC -->
* [CRUD service using Spring Reactor And H2](#crud-service-using-spring-reactor-and-h2)
  * [Description](#description)
  * [Diagram](#diagram)
  * [Postman Collection](#postman-collection)
  * [Dependencies](#dependencies)
  * [Package](#package)
<!-- TOC -->

## Description

CRUD Project with spring Reactor to managing reactive flows. Database was created using H2.
The connection with database was made using R2DBC, special library to manage reactive connection.
In that sense the reactive flow is complete point to point.

Extra: Using Exception Handler using @ControllerAdvice and @ExceptionHandler

* Language: Java 17 
* Framework: SpringBoot 3
* Dependency management: Maven 3.6.3


<img src="docs/markdown/java17-logo.png" alt="logo" style="display: inline-block;margin: 0 10px;width: 100px;height: 50px;background-color: white"/> <img src="docs/markdown/springboot.png" alt="logo" style="display: inline-block;margin: 0 10px;width: 100px;height: 50px;background-color: white"/> <img src="docs/markdown/project-reactor.png" alt="logo" style="display: inline-block;margin: 0 10px;width: 100px;height: 50px;background-color: white"/> <img src="docs/markdown/h2.png" alt="logo" style="display: inline-block;margin: 0 10px;width: 100px;height: 50px;background-color: white"/>


## Diagram

<img src="docs/markdown/Diagrama.svg" alt="logo" style="background-color:white" />

## Postman Collection

Click the link below to download the Postman collection:

[Download Postman Collection](docs/crud-service-rxjava.postman_collection.json)

1. Download the file from the link above.
2. Open Postman.
3. Click "Import" in the top left corner.
4. Select the downloaded `crud-service-reactor-h2.postman_collection.json` file.
5. You can now test the API endpoints using the provided examples.

## Dependencies

- spring-boot-starter-web
- lombok

- h2

## Package

- controllers: Used to define endpoints.
- service: Used to process logic bussines.
- repository: Used to connect with relational databases .
- proxy.client: Implements Classes to connect with other REST Services.
- config: Used to configure the project behaviour. 
- model: Used to create simples object like POJOs, DTOs.
