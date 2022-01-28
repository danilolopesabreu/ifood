package com.github.danilolopesabreu.ifood.aplication.restaurant.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.github.danilolopesabreu.ifood.aplication.restaurant.dto.RestaurantDTO;
import com.github.danilolopesabreu.ifood.domain.restaurant.Restaurant;

@Mapper(componentModel = "cdi")
public interface RestaurantMapper {
	
	public Restaurant toRestaurant(RestaurantDTO dto);
	
	//max one target
	public void toRestaurantUpdate(RestaurantDTO dto, @MappingTarget Restaurant obj);
	
	@Mapping(target = "creationDate", dateFormat = "dd/MM/yyyy HH:mm:ss")
	public RestaurantDTO toRestaurantDTO(Restaurant obj);
	
	public List<RestaurantDTO> toRestaurantsDTO(List<Restaurant> list);
	
}
