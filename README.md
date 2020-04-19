# reactive-service-receiver
This template is a Spring Reactive Service Receiver. 

This service template will allow you to create multi chained microservices using a non-blocking pattern. Simply vcopy this template for other applications and change the endpoint name.

You can also create services using this template and register them with a Kubernetes or some registration and discovey tool like Consul or Eureka.

The project performs the following actions.

1. Let's you send a incoming message to the Asynchronous Rest Controller to be processed.
2. Persists the Message Request into a H2 Database.
3. Forward the request to another service or allows you to send a result request to another service within a service chain.

The service is presented as is with no support or warranties.

ENJOY!

