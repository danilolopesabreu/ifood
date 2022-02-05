package com.github.danilolopesabreu.ifood.aplication.resources.graphql;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;

import com.github.danilolopesabreu.ifood.aplication.restaurant.dto.DishDTO;
import com.github.danilolopesabreu.ifood.aplication.restaurant.dto.RestaurantDTO;
import com.github.danilolopesabreu.ifood.aplication.restaurant.mapper.RestaurantMapper;
import com.github.danilolopesabreu.ifood.infrastructure.restaurant.DishPanacheRepository;
import com.github.danilolopesabreu.ifood.infrastructure.restaurant.RestaurantPanacheRepository;

@GraphQLApi
public class RestaurantResource {

	@Inject
	RestaurantPanacheRepository restaurantPanacheRepository;
	
	@Inject
	DishPanacheRepository dishPanacheRepository;
	
	@Inject
	RestaurantMapper restaurantMapper;
	
	@Query("findRestaurants")
	@Description("List all restaurants")
	public List<RestaurantDTO> findRestaurants (){
		return restaurantMapper.toRestaurantsDTO(restaurantPanacheRepository.listAll());
	}
	
	@Query("findRestaurantsByDish")
	@Description("List all restaurants by dish")
	public List<RestaurantDTO> findRestaurantsByDish(@Source DishDTO dishDTO) {
//		dishPanacheRepository.find("", null);
		return null;
	}
	
	@Query("findRestaurantsById")
	@Description("List one restaurant by id")
	public RestaurantDTO findRestaurantsById(@Name("idRestaurant") Long idRestaurant) {
		return restaurantMapper.toRestaurantDTO(restaurantPanacheRepository.findById(idRestaurant));
	}
	
	@Mutation
	@Description("Update one restaurant")
	public RestaurantDTO updateName(RestaurantDTO restaurantDTO) {
		return restaurantDTO;
	}
	
}
