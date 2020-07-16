# Centime Assignment

## Task 1 - Microservices
This task is to host three Microservices and have a inter-commnunication. All the three microservices uses a discovery client pattern.
There is a eureka registry service which does the discovery of microservices.

### Service One
* Exposes a `GET /serviceone/check` - Which returns Up if service is running.
* Exposed a `POST /serviceone/concatenate` - Which concatenates the Hello, Name, and Surname by calling service two and three. Json request is validated.

### Service Two
* Exposes a `GET /hello` - Which returns Hello which service one consumes.

### Service Three
* Exposes a `POST /concatenate` - Which concatenates Name and Surname which service one consumes.

#### API documentation can be accessed using below cloud url.
    *http://ec2-3-23-20-42.us-east-2.compute.amazonaws.com/swagger-ui.html*

## Task 2 - Nested relationships with embedded H2 db connection

* Exposes a `GET /people` - Which returns nested relationships with time complexity O(3N).
* Exposed a `GET /people/{id}` - Which returns a record based on ID.

### API documentation can be accessed using below cloud url.
    *http://ec2-18-224-8-131.us-east-2.compute.amazonaws.com:9090/swagger-ui.html*

## Future Improvement
* Need work on tracing the requests spanning on the microservices.
* Need work on custom method level annotation to print params in logs.


## Demo Setup
1. Checkout project from https://github.com/tarun38716/CentimeAssignment.git
2. Go to the root folder and run `docker-compose up -d`
3. Access H2 in-memory console using http://localhost:9090/console. [Get jdbc info from application.properties of task2 Service]
