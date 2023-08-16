# JTA Example

To run the example, simply type **docker compose up** to start Artemis broker, the broker directory is mounted under broker folder under the project, so you can change the settings on the fly.

This example shows how JTA transaction is working, you can see different behaviours by calling POST APIs.

This API shows basic setup for JTA transaction

```
$ curl -d '{"item":"apple", "quantity": 100}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/order/transactions
```

To rollback the transaction, the database transction is rolled back also JMS message won't deliver to the listener
```
$ curl -d '{"item":"apple", "quantity": 100}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/order/transactions/rollback
```

JTA will commit transaction even though listener throws an exception but it will resend failed message based on broker settings.
```
$ curl -d '{"item":"apple", "quantity": 100}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/order/transactions/consumer/exception
```
```
<max-delivery-attempts>2</max-delivery-attempts>
```
