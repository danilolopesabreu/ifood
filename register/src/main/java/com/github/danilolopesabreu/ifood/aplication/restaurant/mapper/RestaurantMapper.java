package com.github.danilolopesabreu.ifood.aplication.restaurant.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.github.danilolopesabreu.ifood.aplication.restaurant.dto.RestaurantDTO;
import com.github.danilolopesabreu.ifood.domain.restaurant.Restaurant;

@Mapper(componentModel = "cdi")
public interface RestaurantMapper {
	
	public Restaurant toRestaurant(RestaurantDTO dto);
	
	public RestaurantDTO fromRestaurant(Restaurant obj);
	
	public List<RestaurantDTO> fromRestaurants(List<Restaurant> list);
	
}
