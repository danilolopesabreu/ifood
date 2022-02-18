package com.github.danilolopesabreu.ifood.aplication.resources.graphql;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;
import org.mapstruct.Context;

import com.github.danilolopesabreu.ifood.aplication.restaurant.dto.DishDTO;
import com.github.danilolopesabreu.ifood.aplication.restaurant.dto.RestaurantDTO;
import com.github.danilolopesabreu.ifood.aplication.restaurant.mapper.CycleAvoidingMappingContext;
import com.github.danilolopesabreu.ifood.aplication.restaurant.mapper.DishMapper;
import com.github.danilolopesabreu.ifood.aplication.restaurant.mapper.RestaurantMapper;
import com.github.danilolopesabreu.ifood.infrastructure.restaurant.DishPanacheRepository;
import com.github.danilolopesabreu.ifood.infrastructure.restaurant.RestaurantPanacheRepository;

import io.quarkus.cache.CacheResult;
import io.quarkus.panache.common.Parameters;

@GraphQLApi
public class RestaurantResource {

	@Inject
	RestaurantPanacheRepository restaurantPanacheRepository;
	
	@Inject
	DishPanacheRepository dishPanacheRepository;
	
	@Inject
	RestaurantMapper restaurantMapper;
	
	@Inject
	DishMapper dishMapper;
	
	@Query("findRestaurants")
	@Description("List all restaurants")
	public List<RestaurantDTO> findRestaurants (){
		return restaurantMapper.toRestaurantsDTO(restaurantPanacheRepository.listAll(), new CycleAvoidingMappingContext());
	}
	
	@Query("findRestaurantsByDish")
	@Description("List all restaurants by dish")
	@CacheResult(cacheName = "findRestaurantsByDish")
	public List<DishDTO> findRestaurantsByDish(@Source DishDTO dishDTO) {
		System.out.println(dishDTO);
		
		return dishMapper.toDishesDtos(
				dishPanacheRepository.find(
						"select d from Dish d JOIN FETCH d.restaurant as r where d.name like :name",  
						 Parameters.with("name", "%"+dishDTO.getName()+"%")).list(), new CycleAvoidingMappingContext()
				);
	}
	
	@Query("findRestaurantsById")
	@Description("List one restaurant by id")
	public RestaurantDTO findRestaurantsById(@Name("idRestaurant") Long idRestaurant) {
		return restaurantMapper.fromRestaurantDTO(restaurantPanacheRepository.findById(idRestaurant), new CycleAvoidingMappingContext());
	}
	
	@Mutation
	@Description("Update one restaurant")
	public RestaurantDTO updateName(RestaurantDTO restaurantDTO) {
		return restaurantDTO;
	}
	
}
