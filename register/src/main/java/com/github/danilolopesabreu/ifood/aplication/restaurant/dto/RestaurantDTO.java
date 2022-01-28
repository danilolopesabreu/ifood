package com.github.danilolopesabreu.ifood.aplication.restaurant.dto;

public class RestaurantDTO {

	private String owner;

	private String fein;// Federal Employer Identification Number

	private String name;

	private LocationDTO location;

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

}
