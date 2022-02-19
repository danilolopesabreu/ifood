package com.github.danilolopesabreu.ifood.aplication.restaurant.dto;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.github.danilolopesabreu.ifood.aplication.dto_validation.ValidDto;
import com.github.danilolopesabreu.ifood.aplication.dto_validation.ValidateDto;

@ValidateDto
public class LocationDTO implements ValidDto {

	@NotNull
	private Double latitude;

	@NotNull
	private Double longitude;

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public LocationDTO() {
		// TODO Auto-generated constructor stub
	}

	public LocationDTO(@NotNull Double latitude, @NotNull Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Override
	public boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
		// return ValidDto.super.isValid(constraintValidatorContext);
		constraintValidatorContext.disableDefaultConstraintViolation();
		if (this.longitude != null && this.longitude.equals(555D)) {
			constraintValidatorContext.buildConstraintViolationWithTemplate("Longitude is equal 555.")
					.addPropertyNode("longitude").addConstraintViolation();
			return false;
		}
		return true;
	}

}
