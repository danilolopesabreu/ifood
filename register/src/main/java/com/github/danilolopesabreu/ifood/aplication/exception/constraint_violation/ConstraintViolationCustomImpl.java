package com.github.danilolopesabreu.ifood.aplication.exception.constraint_violation;

import java.io.Serializable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintViolation;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class ConstraintViolationCustomImpl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Schema(description = "Property path, ex: name, person.address.street", required = false)
	private final String property;
	
	@Schema(description = "Property error description", required = true)
	private final String message;
	
	private ConstraintViolationCustomImpl(ConstraintViolation<?> violation) {
		this.message = violation.getMessage();
		this.property = Stream.of(violation.getPropertyPath().toString().split("\\.")).skip(2).collect(Collectors.joining("."));
	}
	
	private ConstraintViolationCustomImpl(String property, String message) {
		this.message = message;
		this.property = property;
	}
	
	public static ConstraintViolationCustomImpl of(ConstraintViolation<?> violation) {
		return new ConstraintViolationCustomImpl(violation);
	}
	
	public static ConstraintViolationCustomImpl of(String violation) {
		return new ConstraintViolationCustomImpl(null, violation);
	}
	
	public String getProperty() {
		return property;
	}
	
	public String getMessage() {
		return message;
	}
	

}
