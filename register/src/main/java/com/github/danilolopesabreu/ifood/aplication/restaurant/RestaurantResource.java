package com.github.danilolopesabreu.ifood.aplication.restaurant;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.github.danilolopesabreu.ifood.aplication.exception.constraint_violation.ConstraintViolationResponse;
import com.github.danilolopesabreu.ifood.aplication.restaurant.dto.DishDTO;
import com.github.danilolopesabreu.ifood.aplication.restaurant.dto.RestaurantDTO;
import com.github.danilolopesabreu.ifood.aplication.restaurant.mapper.DishMapper;
import com.github.danilolopesabreu.ifood.aplication.restaurant.mapper.RestaurantMapper;
import com.github.danilolopesabreu.ifood.domain.restaurant.Dish;
import com.github.danilolopesabreu.ifood.domain.restaurant.Restaurant;
import com.github.danilolopesabreu.ifood.infrastructure.restaurant.DishPanacheRepository;
import com.github.danilolopesabreu.ifood.infrastructure.restaurant.RestaurantPanacheRepository;

@Path("/restaurants")
@Tag(name="Restaurants Resources")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestaurantResource {

	@Inject
	protected RestaurantMapper restaurantMapper;
	
	@Inject
	protected DishMapper dishMapper;
	
	@Inject
	protected RestaurantPanacheRepository restaurantPanacheRepository;
	
	@Inject
	protected DishPanacheRepository dishPanacheRepository;
	
	@POST
	@Transactional
	@APIResponse(responseCode = "201", description = "Restaurant Successfully Registered")
	@APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = ConstraintViolationResponse.class)))
	public void create(@Valid final RestaurantDTO dto) {
		final Restaurant objRestaurant = this.restaurantMapper.toRestaurant(dto);
		objRestaurant.referenceRestaurantLocation();
		restaurantPanacheRepository.persist(objRestaurant);
	}
	
	@GET
	public List<RestaurantDTO> read() {
		return this.restaurantMapper.toRestaurantsDTO(restaurantPanacheRepository.listAll());
	}
	
	@PUT
	@Path("{id}")
	@Transactional
	public void update(@PathParam("id") Long id, RestaurantDTO dto) {
		
		Optional<Restaurant> restaurantOp = restaurantPanacheRepository.findByIdOptional(id);
		
		if(restaurantOp.isEmpty()) {
			throw new NotFoundException();
		}
		
		Restaurant objRestaurant = restaurantOp.get();		
		
		restaurantMapper.toRestaurantUpdate(dto, objRestaurant);
		
		restaurantPanacheRepository.persist(objRestaurant);		
	}
	
	@DELETE
	@Path("{id}")
	@Transactional
	public void delete(@PathParam("id") Long id) {
		if(!restaurantPanacheRepository.deleteById(id)) {
			throw new NotFoundException();
		}
	}
	
	@GET
	@Path("{idRestaurant}/dishes")
	@Transactional
	@Tag(name="Dishes Resources")
	public List<DishDTO> readDishes(@PathParam("idRestaurant") Long idRestaurant) {
		Optional<Restaurant> restaurantOp = restaurantPanacheRepository.findByIdOptional(idRestaurant);

		if(restaurantOp.isEmpty()) {
			throw new NotFoundException("Restaurant Not Found");
		}
		
		return this.dishMapper.fromDishes(restaurantOp.get().getDishes());
	}
	
	@POST
	@Path("{idRestaurant}/dishes")
	@Transactional
	@Tag(name="Dishes Resources")
	public void createDish(@PathParam("idRestaurant") Long idRestaurant, DishDTO dto) {
		Optional<Restaurant> restaurantOp = restaurantPanacheRepository.findByIdOptional(idRestaurant);

		if(restaurantOp.isEmpty()) {
			throw new NotFoundException("Restaurant Not Found");
		}
		
		Dish objDish = dishMapper.toDish(dto);
		
		objDish.setRestaurant(restaurantOp.get());
		this.dishPanacheRepository.persist(objDish);
		
	}
	
	@DELETE
	@Path("{idRestaurant}/dishes/{id}")
	@Transactional
	@Tag(name="Dishes Resources")
	public void deleteDish(@PathParam("idRestaurant") Long idRestaurant, @PathParam("id") Long idDish) {
		if(!dishPanacheRepository.deleteById(idDish)) {
			throw new NotFoundException("Dish Not Found");
		}
	}
	
	@PUT
	@Path("{idRestaurant}/dishes/{id}")
	@Transactional
	@Tag(name="Dishes Resources")
	public void updateDish(@PathParam("idRestaurant") Long idRestaurant, @PathParam("id") Long idDish, DishDTO dto) {
		Optional<Dish> dishOp = dishPanacheRepository.findByIdOptional(idDish);
		
		if(dishOp.isEmpty()) {
			throw new NotFoundException("Dish Not Found");
		}
		
		dishOp.get().setName(dto.getName());
		dishOp.get().setPrice(dto.getPrice());
		
		dishPanacheRepository.persist(dishOp.get());
		
	}
	
}
