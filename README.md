## Spring Boot Employee Rest Service CRUD example

- build the project: `mvn clean install`
- run the application: `mvn spring-boot:run` or run `SpringBootRestCrudApplication.java`

- get all employees
GET http://localhost:8080/employees

- get employee by id
GET http://localhost:8080/employees/1

- delete employee by id
DELETE http://localhost:8080/employees/1

- add new employee
POST http://localhost:8080/employees/4
also set http header `Content-Type` as `application/json`
```
{
    "employeeId": 4,
    "name": "Abhishek"
}
```

- update employee
PUT http://localhost:8080/employees/4
also set http header `Content-Type` as `application/json`
```
{
    "employeeId": 4,
    "name": "Abhishek D"
}
```

The service return json response by default. Append .xml to endpoint or set `Accept` http header as `application/xml` to get xml response

Capability to return XML response comes by just adding below dependency to POM

```
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
</dependency>
```