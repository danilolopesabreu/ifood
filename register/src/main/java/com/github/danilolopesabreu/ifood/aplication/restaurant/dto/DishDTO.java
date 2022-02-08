package com.github.danilolopesabreu.ifood.aplication.restaurant.dto;

import java.math.BigDecimal;

public class DishDTO {

	private String name;

	private String description;

	private BigDecimal price;
	
	private RestaurantDTO restaurantDTO;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public RestaurantDTO getRestaurantDTO() {
		return restaurantDTO;
	}

	public void setRestaurantDTO(RestaurantDTO restaurantDTO) {
		this.restaurantDTO = restaurantDTO;
	}

	@Override
	public String toString() {
		return "DishDTO [name=" + name + ", description=" + description + ", price=" + price + ", restaurantDTO="
				+ restaurantDTO + "]";
	}
	
}
