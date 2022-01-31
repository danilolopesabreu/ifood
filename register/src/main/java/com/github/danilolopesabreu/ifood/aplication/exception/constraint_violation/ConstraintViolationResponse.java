package com.github.danilolopesabreu.ifood.aplication.exception.constraint_violation;

import java.util.LinkedList;
import java.util.List;

import javax.validation.ConstraintViolationException;

public class ConstraintViolationResponse {
	
	private final LinkedList<ConstraintViolationCustomImpl> violations = new LinkedList<ConstraintViolationCustomImpl>();
	
	private ConstraintViolationResponse(ConstraintViolationException exception) {
		exception.getConstraintViolations()
					.forEach(
							violation -> violations.add(ConstraintViolationCustomImpl.of(violation))
						);
	}
	
	public static ConstraintViolationResponse of(ConstraintViolationException exception) {
		return new ConstraintViolationResponse(exception);
	}
	
	public List<ConstraintViolationCustomImpl> getViolations(){
		return violations;
	}
	
}
