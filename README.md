This is an ecommerce application build using Spring Boot 

The application uses microservices for the different modules. 

The following services are built

CustomerService - This service is responsible for creating a customer <br/>
ProductService - This service is used to create and purchase products <br/>
OrderService - This service is used to create an order <br/>
PaymentService - This service is used store the payment for the order <br/>
ConfigServer - This service has the centralized configuration for all the microservices <br/>
DiscoveryService - This service is the Eureka server for service registration and discovery
ApiGatewayService - This is the API gateway to route requests to the appropriate services


The following modules have been used to build the application

Spring Cloud Config Server - Centralized configurations
Eureka Server - For the service registration and discovery of microservices <br/>
Eureka Discovery Client - For registeration of microservices with the discovery server <br/>
KeyCloak has been used for authentication and authorization for the requests to the apis

Mongo DB and PostgreSQL have been used to store data in database <br/>
Feign Client and/or RestTemplate have been used for inter service communication <br/>
ZipKin has been used for tracing requests <br/>
Spring Kafka has been used for async communication of messages between services <br/>
MailDev has been used for sending emails <br/>

How to start the application

A docker compose file has been given to start up the required tools for the application to work <br/>
Run the docker compose file by issuing the following command at the root directory of the application<br/>
docker compose up -d <br/>
This should bring up the required services <br/>
Once everything is up and running, now is the time to bring up individual microservices <br/>
- config
- discovery
- customer
- payment
- product
- order
- notification
- apigateway
  <br/>

The Eureka server should have been started on port 8761 and in order to see the dashboard just head to http://localhost:8761/
<br/>
This should show you all the services running
<br/>
For the different requests since they are routed via the apigateway, you can call the different apis as follows
<br/>
[POST] http://localhost:8222/api/v1/customer - CREATE A CUSTOMER
<br/>
[GET] http://localhost:8222/api/v1/customer - GET THE LIST OF CUSTOMERS
<br/>
[POST] http://localhost:8222/api/v1/product - CREATE A PRODUCT
<br/>
[GET] http://localhost:8222/api/v1/product - GET THE LIST OF PRODUCTS
<br/>
[POST] http://localhost:8222/api/v1/order - CREATE AN ORDER

<br/>

In order to trigger the requests, you need to create a security realm in keycloak with the name micro-services and then create a client using client credentials
<br/>
Once you have created the client credentials, then you can get the auth server and token endpoint from keycloak and then generate a token in postman using oAuth2.0
<br/>
Use the client credentials way to generate the token. Once you get the token from keycloak, use that token to trigger the requests above
<br/>
Feel free to add comments / suggestions. If you have any questions, leave me an email at romeoopk@gmail.com 



