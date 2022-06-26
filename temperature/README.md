# peatix-temperature-conversion
temperature convertor spring boot project

### Requirement/Assumptions
- Develop HTTP API to convert Farenheit to Celcius and vice vera
- Assumed if the source unit and destination unit is same then output as input value , It will ensure not to break the business functionality.
- For any invalid request , it will through the exception as "BAD REQUEST"
- If Service is not ready within the configured time , the API will throw an exception "Service not ready exception"
- At the end of the API call, application will call the Post construct.

## Instructions on how to build and run your app
There are several ways to run a Spring Boot application on your local machine.

### To Build
```shell 
gradle build
```

### To Run
```shell
gradle bootRun
```

From Ide

One way is to execute the `main` method in the `com.peatix.conversion.TemperatureConvertorApplication` class from your IDE.

Alternatively you can use the [Spring Boot gradle plugin]

### Approach - Why REST ?
````
--  One of the most popular types of API is REST or, as they’re sometimes known, RESTful APIs. There are many benefits of REST or RESTful APIs — theye designed to take advantage of existing protocols. While REST - or Representational State Transfer - can be used over nearly any protocol, when used for web APIs it typically takes advantage of HTTP. This means that developers have no need to install additional software or libraries when creating a REST API.
--  One of the key advantages of REST APIs is that they provide a great deal of flexibility. Data is not tied to resources or methods, so REST can handle multiple types of calls, return different data formats and even change structurally with the correct implementation of hypermedia. This flexibility allows developers to build an API that meets your needs while also meeting the needs of very diverse customers. 
--  Rest API has Client-server, Stateless,Uniform Interface,Code on Demand and Caching features, in future if we need to acheive the scalablity and caching for certian inputs combination we can perform this easiliy with REST. 
````
## POST /temperature/v1/convert

### Header
````
 -H 'Content-Type: application/json'
````
### Request JSON
````
{
    "sourceUnit": "string",
    "destinationUnit": "string",
    "value": "string"
}
````
| Name            | Nullable? | Value Type | Example      | Description                                                                            |
|-----------------|-----------|------------|--------------|----------------------------------------------------------------------------------------|
| sourceUnit      | X         | String     | "Celsius"    | Possible combination - "Celsius"  or "Fahrenheit, if any other values then BAD_REQUEST |                                                                                                  
| destinationUnit | X         | String     | "Fahrenheit" | Possible combination - "Celsius"  or "Fahrenheit, if any other values then BAD_REQUEST |
| value           | X         | String     |              |                                                                                        |


#### Status Codes

| Sr.No | Status Code | Details           |
|-------|-------------| ----------------- |
| 1     | 200         | Success           |
| 2     | 400         | Bad Request   |
| 3     | 503         | Service Unavailable |

### Response
````
{
    "inputUnit": "string",
    "inputValue": double,
    "convertedValue": double,
    "convertedUnit": "string"
}
````
| Name           | Nullable? | Value Type | Example      | Description |
|----------------|-----------|------------|--------------|-------------|
| inputUnit      | X         | String     | "Celcius"    |             |                                                                                                  
| inputValue     | X         | Double     | 100.0        |             |
| convertedValue | X         | Double     | 212.0        |             |
| convertedUnit  | X         | String     | "Fahrenheit" |             |


### curl request

```
Example for Celcius -> Fahrenheit

curl --location --request POST 'http://localhost:8080/temperature/v1/convert' \
--header 'Content-Type: application/json' \
--data-raw '{
"sourceUnit": "Celcius",
"destinationUnit": "Fahrenheit",
"value": "100"
}'

Example for Fahrenheit -> Celcius

curl --location --request POST 'http://localhost:8080/temperature/v1/convert' \
--header 'Content-Type: application/json' \
--data-raw '{
"sourceUnit": "Fahrenheit",
"destinationUnit": "Celcius",
"value": "77"
}'
```

## Instruction on how to run unit and integration tests.
Run below command from your shell.
```shell
gradle Test
```


### What you’ll need
- A favorite text editor or IDE
- JDK 11 or later
- Install Gradle

### status 400
```
{
    "timestamp": "2022-06-26T15:00:12.072+00:00",
    "status": 400,
    "error": "Bad Request",
    "path": "/temperature/v1/convert"
}
```

### status 503
```
{
    "timestamp": "2022-06-26T15:00:12.072+00:00",
    "status": 503,
    "error": "Service Unavailable",
    "path": "/temperature/v1/convert"
}

```
### status 200 - when(same source and destination unit)
```
{
    "inputUnit": "FAHRENHEIT",
    "inputValue": 100.0,
    "convertedValue": 100.0,
    "convertedUnit": "FAHRENHEIT"
}

```

### status 200 
```
{
    "inputUnit": "FAHRENHEIT",
    "inputValue": 100.0,
    "convertedValue": 37.77777777777778,
    "convertedUnit": "CELSIUS"
}

```