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


## Steps to run in docker container


Steps to run spring boot application inside docker container

* Install Docker desktop on your system. For mac, refer: https://docs.docker.com/docker-for-mac/install/

* Start docker daemon on your system by starting Docker desktop

* Create a very simple spring boot app (could create hello world rest service)

* Create a Dockerfile inside root of your application repo, with below content

This basically means, that firstly you are creating a image using openjdk 8 image, copying you spring boot app jar to root with name `spring-boot-rest-crud.jar`, exposing the port 8080 and then specifying endpoint to start your spring boot jar (its a fat jar with embedded tomcat)    


```
FROM openjdk:8
ADD /target/spring-boot-rest-crud-0.0.1-SNAPSHOT.jar spring-boot-rest-crud.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-boot-rest-crud.jar"]
```

* Build image using below command


```
~/Documents/practice_workspace/spring-boot-rest-crud(git::master):➔ docker build -f Dockerfile -t spring-boot-rest .
Sending build context to Docker daemon  15.35MB
Step 1/4 : FROM openjdk:8
8: Pulling from library/openjdk
146bd6a88618: Pull complete
9935d0c62ace: Pull complete
db0efb86e806: Pull complete
e705a4c4fd31: Pull complete
3d3bf7f7e874: Pull complete
49371c5b9ff6: Pull complete
3f7eaaf7ad75: Pull complete
Digest: sha256:7b7408b997615b4d6aaf6c1f0de8a32182497250288ee0a27b4e98cf14a52fb3
Status: Downloaded newer image for openjdk:8
 ---> 8c6851b1fc09
Step 2/4 : ADD /target/spring-boot-rest-crud-0.0.6-SNAPSHOT.jar spring-boot-rest-crud.jar
 ---> 8648b6664b4b
Step 3/4 : EXPOSE 8080
 ---> Running in 50374453bade
Removing intermediate container 50374453bade
 ---> 39caa5f89a1b
Step 4/4 : ENTRYPOINT ["java", "-jar", "spring-boot-rest-crud.jar"]
 ---> Running in 912fa5c31e60
Removing intermediate container 912fa5c31e60
 ---> d696d6b72a05
Successfully built d696d6b72a05
Successfully tagged spring-boot-rest:latest
```


* Check generated docker image using below command:


```
~/Documents/practice_workspace/spring-boot-rest-crud(git::master):➔ docker images
REPOSITORY        TAG                 IMAGE ID              CREATED               SIZE
spring-boot-rest     latest              d696d6b72a05      58 seconds ago     503MB
openjdk                  8                     8c6851b1fc09       32 hours ago          488MB
busybox                 latest              19485c79a9bb       4 months ago        1.22MB
```


* Run docker container using below command, this should start spring boot app


```
~/Documents/practice_workspace/spring-boot-rest-crud(git::master):➔ docker run -p 8080:8080 spring-boot-rest

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.4.3.RELEASE)

2020-01-24 08:47:17.745  INFO 1 --- [           main] org.vs.SpringBootRestCrudApplication     : Starting SpringBootRestCrudApplication v0.0.6-SNAPSHOT on 3aa12a1e1204 with PID 1 (/spring-boot-rest-crud.jar started by root in /)
2020-01-24 08:47:17.747  INFO 1 --- [           main] org.vs.SpringBootRestCrudApplication     : No active profile set, falling back to default profiles: default
2020-01-24 08:47:17.795  INFO 1 --- [           main] ationConfigEmbeddedWebApplicationContext : Refreshing org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@14514713: startup date [Fri Jan 24 08:47:17 UTC 2020]; root of context hierarchy
...
...
2020-01-24 08:47:19.732  INFO 1 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2020-01-24 08:47:19.773  INFO 1 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2020-01-24 08:47:19.776  INFO 1 --- [           main] org.vs.SpringBootRestCrudApplication     : Started SpringBootRestCrudApplication in 2.486 seconds (JVM running for 2.85)
2020-01-24 08:47:31.308  INFO 1 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring FrameworkServlet 'dispatcherServlet'
2020-01-24 08:47:31.309  INFO 1 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization started
2020-01-24 08:47:31.326  INFO 1 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization completed in 17 ms
```


* Access your application by calling any of the endpoint, example: http://localhost:8080/employees

* You can also check logs from application to validate


```
2020-01-24 08:47:31.350  INFO 1 --- [nio-8080-exec-1] org.vs.resource.EmployeeResource         : returning all employees
2020-01-24 08:47:32.106  INFO 1 --- [nio-8080-exec-2] org.vs.resource.EmployeeResource         : returning all employees
```

