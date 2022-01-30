package com.github.danilolopesabreu.ifood.aplication.dto_validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValidDtoValidator.class })
@Documented
public @interface ValidateDto {
	
	String message() default "{com.github.danilolopesabreu.ifood.aplication.dto.ValidateDto.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
