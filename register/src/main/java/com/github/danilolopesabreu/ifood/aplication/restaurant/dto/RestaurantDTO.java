package com.github.danilolopesabreu.ifood.aplication.restaurant.dto;

import javax.validation.ConstraintValidatorContext;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.github.danilolopesabreu.ifood.aplication.dto.ValidDto;
import com.github.danilolopesabreu.ifood.aplication.dto.ValidateDto;

@ValidateDto
public class RestaurantDTO implements ValidDto {

	@NotEmpty 
	private String owner;

	@NotEmpty
	@Pattern(regexp = "[0-9]{2}", message = "Wrong format")
	private String fein;// Federal Employer Identification Number

	@Size(min = 3, max=30)
	@NotEmpty
	private String name;

	@Valid
	private LocationDTO location;
	
	private String creationDate;

	@Override
	public boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
		//return ValidDto.super.isValid(constraintValidatorContext);
		if(this.fein.equals("66")) {
			constraintValidatorContext
				.buildConstraintViolationWithTemplate("The FEIN already exists.")
					.addPropertyNode("fein")
					.addConstraintViolation();
			return false;
		}
		return true;
	}
	
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getFein() {
		return fein;
	}

	public void setFein(String fein) {
		this.fein = fein;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocationDTO getLocation() {
		return location;
	}

	public void setLocation(LocationDTO location) {
		this.location = location;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

}
