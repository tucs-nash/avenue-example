# avenue-example

This is my solution of avenue test project. I used the JAX-RS integrated with Spring Boot.

## Unit Tests

Execute the maven command 'test' to run the project tests

```
mvn test
```

## Build
Execute the maven command 'clean install' to build. This command will run the unit tests as well

```
mvn clean install
```

## Start application
Execute the maven command 'spring-boot:run' to start the application.

```
mvn spring-boot:run
```

## API Tests

Using your terminal, execute the curl command for each operations:

### Create product

```
curl -X POST -H 'Content-Type: application/json' -d '{"name":"Samsung Note","description":"Samsung Note Mobile"}' http://localhost:8080/products
```

### Create child product

```
curl -X POST -H 'Content-Type: application/json' -d '{"name":"Samsung Note 64MB","description":"Samsung Note Mobile  64MB","parentProduct":{"id":1,"name":"Samsung Note","description":"Samsung Note Mobile"}}' http://localhost:8080/products
```

### Update product

```
curl -X PUT -H 'Content-Type: application/json' -d '{"name":"Samsung Note 128MB","description":"Samsung Note Mobile 128MB","parentProduct":{"id":1,"name":"Samsung Note","description":"Samsung Note Mobile"}}' http://localhost:8080/products/2
```

### Delete product

```
curl -X DELETE http://localhost:8080/products/2
```

### Create image

```
curl -X POST -H 'Content-Type: application/json' -d '{"type":"image 1"}' http://localhost:8080/products/1/images
```

### Update image

```
curl -X PUT -H 'Content-Type: application/json' -d '{"type":"image 2"}' http://localhost:8080/products/1/images/1
```

### Delete image

```
curl -X DELETE http://localhost:8080/products/1/images/1
```


### Get all products excluding relationships

```
curl http://localhost:8080/products
```

### Get all products including relationships

```
curl http://localhost:8080/products/complete
```

### Get product by id excluding relationships

```
curl http://localhost:8080/products/1
```

### Get product by id including relationships

```
curl http://localhost:8080/products/1/complete
```

### Get set of images for specific product

```
curl http://localhost:8080/products/1/images
```

### Get set of child products for specific product

```
curl http://localhost:8080/products/1/children
```
