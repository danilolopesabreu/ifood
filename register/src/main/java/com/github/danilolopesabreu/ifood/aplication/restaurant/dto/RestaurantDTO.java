package com.github.danilolopesabreu.ifood.aplication.restaurant.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RestaurantDTO {

	@NotEmpty 
	private String owner;

	@NotEmpty
	@Pattern(regexp = "[0-9]{2}")
	private String fein;// Federal Employer Identification Number

	@Size(min = 3, max=30)
	@NotEmpty
	private String name;

	@NotNull
	private LocationDTO location;
	
	private String creationDate;

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
