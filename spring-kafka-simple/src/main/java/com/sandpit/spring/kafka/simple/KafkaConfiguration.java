package com.sandpit.spring.kafka.simple;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.converter.BatchMessagingMessageConverter;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConfiguration {
	
	@Bean
	public CommonErrorHandler errorHandler(KafkaOperations<Object, Object> ops) {
		return new DefaultErrorHandler(
				new DeadLetterPublishingRecoverer(ops), 
				new FixedBackOff(1000, 3));
	}	

	@Bean
	public RecordMessageConverter recordMessageConverter(){
		return new JsonMessageConverter();
	}
	
	@Bean
	public NewTopic dlt() {
		return TopicBuilder.name("order.DLT").partitions(1).replicas(1).build();
	}	
	
	@Bean
	public NewTopic order() {
		return TopicBuilder.name("order").partitions(1).replicas(1).build();
	}

	@Bean
	public NewTopic orderTrans() {
		return TopicBuilder.name("order.trans").partitions(1).replicas(1).build();
	}

	
	@Bean
	public NewTopic stock() {
		return TopicBuilder.name("stock").partitions(1).replicas(1).build();
	}
	
}
