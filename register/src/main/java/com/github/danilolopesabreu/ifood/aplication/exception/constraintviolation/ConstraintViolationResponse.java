package com.github.danilolopesabreu.ifood.aplication.exception.constraintviolation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

public class ConstraintViolationResponse {
	
	private final List<ConstraintViolationCustomImpl> violations = new ArrayList<ConstraintViolationCustomImpl>();
	
	private ConstraintViolationResponse(ConstraintViolationException exception) {
		exception.getConstraintViolations().forEach(violation -> violations.add(ConstraintViolationCustomImpl.of(violation)));
	}
	
	public static ConstraintViolationResponse of(ConstraintViolationException exception) {
		return new ConstraintViolationResponse(exception);
	}
	
	public List<ConstraintViolationCustomImpl> getViolations(){
		return violations;
	}
	
}
