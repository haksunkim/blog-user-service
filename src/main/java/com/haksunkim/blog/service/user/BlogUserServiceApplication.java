package com.haksunkim.blog.service.user;

import java.util.Arrays;
import java.util.List;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.haksunkim.blog.service.user.amqp.Receiver;


@SpringBootApplication
public class BlogUserServiceApplication {
	final static String QUEUE_NAME = "user";
	final static String EXCHANGE_NAME = "blog";
	final static String ROUTING_KEY = "blog.user";
	
	@Bean
	Queue queue() {
		return new Queue(QUEUE_NAME, false);
	}
	
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(EXCHANGE_NAME);
	}
	
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(QUEUE_NAME);
		container.setMessageListener(listenerAdapter);
		
		return container;
	}
	
	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BlogUserServiceApplication.class, args);
	}	
}
