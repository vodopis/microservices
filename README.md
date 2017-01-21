# Microservices architecture
Demo microservices project built using Spring Boot and Spring Cloud Netflix

### Description
This project provides an example of microservices architecture providing the following:
* Service Discovery (Eureka)
* API Gateway (Zuul)
* Authentication and Authorization
* Implementation of 2 microservices (products and stores) which are able to communicate to each other

On startup, api gateway and microservices will register themselves with Eureka. Requests
are routed though Zuul which runs on port 8080.

### Test preparation
Open 5 terminal tabs in project directory and run supplied commands in order. Wait until each downloads dependencies and fully starts before going to the next one.
1. Eureka service discovery: ./gradlew :service-discovery:bootRun
2. Zuul api gateway: ./gradlew :api-gateway:bootRun
3. Store Service: ./gradlew :store-service:bootRun
4. Product Service: ./gradlew :product-service:bootRun
5. Use for curl commands below

### Interacting with microservices

1. Request bearer token
```
curl test:test123@localhost:8080/oauth/token -d grant_type=client_credentials
```

2. Interact with stores resource - substitute TOKEN with token received from command above
  1. LIST
    ```
    curl localhost:8080/stores -v -H 'Authorization: Bearer TOKEN'
    ```
  2. GET
    ```
    curl localhost:8080/stores/2 -v -H 'Authorization: Bearer TOKEN'
    ```
    There are 3 store objects in memory with ids 1, 2, 3
  3. DELETE
    ```
    curl localhost:8080/stores/2 -v -X DELETE -H 'Authorization: Bearer TOKEN'
    ```
  4. UPDATE
    ```
    curl localhost:8080/stores/3 -v -X PUT -H 'Authorization: Bearer TOKEN' -H 'Content-Type: application/json' -d '{"description":"Updated 3333"}'
    ```
  5. CREATE
    ```
    curl localhost:8080/stores -v -X POST -H 'Authorization: Bearer TOKEN' -H 'Content-Type: application/json' -d '{"id":4, "name":"Object 4", "description":"Description 4"}'
    ```
  6. SEARCH
    ```
    curl localhost:8080/stores/search -v -X POST -H 'Authorization: Bearer TOKEN' -H 'Content-Type: application/json' -d '{"description":"Description 4"}'
    ```

3. Interact with products microservice - endpoints are the same as above starting with /products prefix. There are 3 product objects in memory with ids 100, 101, 102. Note that LIST and GET request will make a request to stores service through the gateway - observe log messages in tab 3.