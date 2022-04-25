# Object-Oriented To Functional Programming

The objective is to learn how to use Functional Programming (FP) techniques such as Pure Functions, Immutability etc.
However, in real world software systems side effects are desired business outcomes. 
This is an exercise to learn how to handle side-effects while using FP.

## The UseCase
The usecase is a simple user registration process.

1. User sends a Registration request with name, email, phoneNumber
2. Validate the input: `name` and `email` should not be blank/empty
3. We need to verify whether a user already registered with same email? 
   If yes, then respond with Conflict Request status with appropriate error message.
4. Persist the user details in DB
5. Publish a UserCreatedEvent to a MessageBroker like Kafka/RabbitMQ

This is a very common requirement in typical enterprise business applications where the business process results in side-effects.

The goal is to learn how to approach this usecase using FP.

## Approaches

1. SpringBoot OO with side-effects

```shell
cd user-management
./mvnw test
./mvnw spring-boot:run
```

```shell
curl --location --request POST 'http://localhost:8080/api/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Siva",
    "email": "siva@gmail.com",
    "phone": "91919191"
}'
```