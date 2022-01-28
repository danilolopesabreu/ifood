package com.github.danilolopesabreu.ifood.aplication.restaurant.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.github.danilolopesabreu.ifood.aplication.restaurant.dto.DishDTO;
import com.github.danilolopesabreu.ifood.domain.restaurant.Dish;

@Mapper(componentModel = "cdi")
public interface DishMapper {
	
	public Dish toDish(DishDTO dto);
	
	public DishDTO fromDish(Dish obj);
	
	public List<DishDTO> fromDishes(List<Dish> list);
	
}
