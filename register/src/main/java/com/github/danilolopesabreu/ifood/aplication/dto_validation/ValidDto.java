package com.github.danilolopesabreu.ifood.aplication.dto_validation;

import javax.validation.ConstraintValidatorContext;

public interface ValidDto {
	
	default boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
		return true;
	}

}
