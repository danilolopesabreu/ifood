package com.github.danilolopesabreu.ifood.aplication.restaurant.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.github.danilolopesabreu.ifood.aplication.dto.ValidDto;
import com.github.danilolopesabreu.ifood.aplication.dto.ValidateDto;

@ValidateDto
public class LocationDTO implements ValidDto{

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

}
