## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [Docker](https://www.docker.com)

## Running the application locally

clone the repo with the comamnd given below: 
```shell
git clone https://github.com/mozammal/user-service.git
```

There are several ways to run a Spring Boot application on your local machine. One way
is to use docker-compose from the command line like the following command:
```shell
mvn clean package
docker-compose up -d
```
Another way is to use maven to run the application from the command line like:

```shell
mvn spring-boot:run
```

The REST APIs documentation can be accessed at 
- [APIs doc link](http://localhost:8080/swagger-ui.html) 

##### Create user with contacts 
 
- [rest endpoint](http://localhost:8080/api/users/)

- Method: POST
- Payload JSON
----

```json
{
  "id": 12,
  "firstName": "mozammal",
  "lastName": "hossain",
  "emails": [
    {
      "mail": "mozammaltomal.100@gmail.com"
    },
    {
      "mail": "mozammaltomal_1001@yahoo.com"
    }
  ],
  "phoneNumbers": [
    {
      "number": "01753193627"
    }
  ]
}

```

##### return user by id 
 
- [rest endpoint](http://localhost:8080/api/users/{id})

- Method: GET 
- example url: http://localhost:8080/api/users/1



