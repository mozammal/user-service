## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [Docker](https://www.docker.com)

## Running the application locally

clone the repo with the command given below: 
```shell
git clone https://github.com/mozammal/user-service.git
```

There are several ways to run a Spring Boot application on your local machine. One way
is to use docker-compose from the command line like the following command:
```shell
cd user-service
mvn clean package
docker-compose up -d
```
Another way is to use maven to run the application from the command line like:

```shell
cd user-service
mvn spring-boot:run
```

The REST APIs documentation can be accessed at 
- [APIs doc link](http://localhost:8080/swagger-ui.html) 

##### Create user with contacts 
 
- [rest endpoint](http://localhost:8080/api/users/)

- Method: POST
- example JSON payload
----

```json
{
  "id": 1,
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
 
- [rest endpoint](http://localhost:8080/api/users/id)

- endpoint: http://localhost:8080/api/users/{id} where id is the user id
- Method: GET 
- example url: http://localhost:8080/api/users/1

##### Add additional mail 
 
- [rest endpoint](http://localhost:8080/api/users/{id}/emails)

- endpoint: http://localhost:8080/api/users/{id}/emails where id is the user id
- Method: POST
- example url: http://localhost:8080/api/users/1/emails
- example payload
----
```json
[
  {
    "mail": "abdul@yahoo.com"
  }
]
```

##### Add additional phonenumber 
 
- [rest endpoint](http://localhost:8080/api//users/{id}/phonenumbers)

- endpoint: http://localhost:8080/api/users/{id}/phonenumbers where id is the user id
- Method: POST
- example url:  http://localhost:8080/api/users/1/phonenumbers
- example payload
----
```json
[
  {
    "number": "+478181811"
  }
]
```

##### Update existing email 
 
- [rest endpoint](http://localhost:8080/api/emails/{id})

- endpoint: http://localhost:8080/api/emails/{id} where id is the email id
- Method: PUT
- example url:  http://localhost:8080/api/emails/12
- example payload
----
```json

  {
    "id": 12,
    "mail": "abdulnew@yahoo.com"
  }
```

##### Update existing phonenumber 
 
- [rest endpoint](http://localhost:8080/api/phonenumbers/{id})

- endpoint: http://localhost:8080/api/phonenumbers/{id} where id is the phonenumber id
- Method: PUT
- example url:  http://localhost:8080/api/phonenumbers/12
- example payload
----
```json

  {
    "id": 12,
    "number": "+472112222"
  }
```

##### Return user by name
 
- [rest endpoint](http://localhost:8080/api/search_users?firstName=x&lastName=y})

- endpoint: http://localhost:8080/api/search_users?firstName=x&lastName=y where firstName and lastName are used for searching by lastName or firstName or by both
- Method: GET
- example url:  http://localhost:8080/api/search_users?firstName=mozammal&lastName=hossain
- example url:  http://localhost:8080/api/search_users?firstName=mozammal
- example url:  http://localhost:8080/api/search_users?lastName=hossain

##### Delete user
 
- [rest endpoint](http://localhost:8080/api/users/{id})

- endpoint: http://localhost:8080/api/users/{id} where id is the user id
- Method: DELETE
- example url:  http://localhost:8080/api/users/1

# Best practices used in this demo app
 
- Services and Controllers are put together in modules that are oriented around functionality
- Controllers delegate business logic to service layer
- Services are built around business capabilities 
- constructor injection was employed
- Bean validation was used
- Global exception handling
- Logging framework used 
- Test coverage 