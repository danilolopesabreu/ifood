package com.github.danilolopesabreu.ifood.aplication.restaurant.mapper;

import java.util.List;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.github.danilolopesabreu.ifood.aplication.restaurant.dto.DishDTO;
import com.github.danilolopesabreu.ifood.domain.restaurant.Dish;

@Mapper(componentModel = "cdi")
public interface DishMapper {
	
	/* https://stackoverflow.com/a/45653375/10634747 */
	DishMapper MAPPER = Mappers.getMapper( DishMapper.class );

	public Dish toDish(DishDTO dto, @Context CycleAvoidingMappingContext context);
	
	public DishDTO toDishDto(Dish obj, @Context CycleAvoidingMappingContext context);
	
	public List<DishDTO> toDishesDtos(List<Dish> list, @Context CycleAvoidingMappingContext context);
	
}
