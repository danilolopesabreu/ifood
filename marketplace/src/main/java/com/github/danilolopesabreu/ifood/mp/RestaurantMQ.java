package com.github.danilolopesabreu.ifood.mp;

import java.util.concurrent.CompletionStage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import io.smallrye.reactive.messaging.amqp.AmqpMessage;
import io.smallrye.reactive.messaging.annotations.Merge;

@ApplicationScoped
public class RestaurantMQ {

	@Inject
	@Channel("REQ_restaurant")
	Emitter<String> emiter;
	
	@Incoming("REQ_restaurant")
//	@Outgoing("RSP_restaurant")
	@Merge
	public CompletionStage<Void> readAndRespondQueue(Message<String> message) {
		System.out.println(message.getClass().getTypeName());
		
		System.out.println("CorrelationId: "+((AmqpMessage<String>) message).getCorrelationId());
		return message.ack();
	}
	
	
}
