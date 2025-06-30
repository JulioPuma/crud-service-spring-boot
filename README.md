#  CRUD service using RXJava3

CRUD Project with RXJava3 managing reactive flows. Database was created internally.

Extra: Using Exception Handler using @ControllerAdvice and @ExceptionHandler

* Language: Java 17 
* Framework: SpringBoot 3
* Dependency management: Maven 3.6.3

<img src="docs/markdown/java17-logo.png" alt="logo" style="height: 50px; width:100px;"/>
<img src="docs/markdown/springboot.png" alt="logo" style="height: 50px; width:100px;"/>
<img src="docs/markdown/reactiveX.png" alt="logo" style="height: 50px; width:100px; background-color:white" />

## Postman Collection

Click the link below to download the Postman collection:

[Download Postman Collection](docs/crud-service-rxjava.postman_collection.json)

1. Download the file from the link above.
2. Open Postman.
3. Click "Import" in the top left corner.
4. Select the downloaded `crud-service-rxjava.postman_collection.json` file.
5. You can now test the API endpoints using the provided examples.

## Dependencies

- spring-boot-starter-web
- lombok
- RxJava3

## Package

- controllers: Used to define endpoints.
- service: Used to process logic bussines.
- repository: Used to connect with relational databases .
- proxy.client: Implements Classes to connect with other REST Services.
- config: Used to configure the project behaviour. 
- model: Used to create simples object like POJOs, DTOs. 