# Spring Kafka

## Setting up DLQ

```
	@Bean
	public CommonErrorHandler errorHandler(KafkaOperations<Object, Object> ops) {
		return new DefaultErrorHandler(
				new DeadLetterPublishingRecoverer(ops), 
				new FixedBackOff(1000, 3));
	}
```

### The runtime exception in listener will be routed to DLQ
```
curl -d '[{"item":"apple", "quantity": 1}]' -H "Content-Type: application/json" -X POST http://localhost:8080/orders
```

## Batch transaction
All three orders will be processed in one transaction

```
$ curl -d '[{"item":"apple", "quantity": 1},{"item":"banana", "quantity": 2}, {"item":"orange", "quantity": 3}]' -H "Content-Type: application/json" -X POST http://localhost:8080/transaction/orders
```