package com.github.danilolopesabreu.ifood.aplication.dto;

import javax.validation.ConstraintValidatorContext;

public interface ValidDto {
	
	default boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
		return true;
	}

}
