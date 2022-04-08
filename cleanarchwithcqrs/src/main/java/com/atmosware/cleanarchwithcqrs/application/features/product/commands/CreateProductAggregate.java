package com.atmosware.cleanarchwithcqrs.application.features.product.commands;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class CreateProductAggregate {
	
	@AggregateIdentifier
	private String productId;
	
	private String productName;

	private double price;

	private String description;
	
	public CreateProductAggregate() {
		
	}
	
	@CommandHandler
	public CreateProductAggregate(CreateProductCommand command) {
		ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
		
		BeanUtils.copyProperties(command, productCreatedEvent);
		
		AggregateLifecycle.apply(productCreatedEvent);
	}
	
	@EventSourcingHandler
	public void on(ProductCreatedEvent productCreatedEvent) {
		this.productId = productCreatedEvent.getProductId();
		this.productName = productCreatedEvent.getProductName();
		this.price = productCreatedEvent.getPrice();
		this.description = productCreatedEvent.getDescription();
	}
}
