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

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import com.github.danilolopesabreu.ifood.aplication.exception.constraint_violation.ConstraintViolationResponse;
import com.github.danilolopesabreu.ifood.aplication.restaurant.dto.DishDTO;
import com.github.danilolopesabreu.ifood.aplication.restaurant.dto.RestaurantDTO;
import com.github.danilolopesabreu.ifood.aplication.restaurant.mapper.CycleAvoidingMappingContext;
import com.github.danilolopesabreu.ifood.aplication.restaurant.mapper.DishMapper;
import com.github.danilolopesabreu.ifood.aplication.restaurant.mapper.RestaurantMapper;
import com.github.danilolopesabreu.ifood.domain.restaurant.Dish;
import com.github.danilolopesabreu.ifood.domain.restaurant.Restaurant;
import com.github.danilolopesabreu.ifood.infrastructure.restaurant.DishPanacheRepository;
import com.github.danilolopesabreu.ifood.infrastructure.restaurant.RestaurantPanacheRepository;

import io.smallrye.reactive.messaging.annotations.Broadcast;

// TODO: Auto-generated Javadoc
/**
 * The Class RestaurantResource.
 */
@Path("/restaurants")
@Tag(name="Restaurants Resources")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RolesAllowed("property")
//@SecurityScheme(
//	securitySchemeName = "ifood-oauth", 
//	type = SecuritySchemeType.OAUTH2, 
//	flows = @OAuthFlows(password = @OAuthFlow(tokenUrl = "http://localhost:8888/auth/realms/ifood/protocol/openid-connect/token"))
//)
//@SecurityRequirement(name = "ifood-oauth", scopes = {}) 
public class RestaurantResource {

	/** The restaurant mapper. */
	@Inject
	protected RestaurantMapper restaurantMapper;
	
	/** The dish mapper. */
	@Inject
	protected DishMapper dishMapper;
	
	/** The restaurant panache repository. */
	@Inject
	protected RestaurantPanacheRepository restaurantPanacheRepository;
	
	/** The dish panache repository. */
	@Inject
	protected DishPanacheRepository dishPanacheRepository;
	
	/** The jwt. */
	@Inject
	JsonWebToken jwt;
	
	/** The sub. */
	@Inject
	@Claim(standard = Claims.sub)
	String sub;
	
	
	@Inject
	@Channel("REQ_restaurant")
	Emitter<RestaurantDTO> emitterRestaurant;
	
	/**
	 * Creates the.
	 *
	 * @param dto the dto
	 */
	@POST
	@Transactional
	@APIResponse(responseCode = "201", description = "Restaurant Registered Successfully")
	@APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = ConstraintViolationResponse.class)))
	public void create(@Valid final RestaurantDTO dto) {
		final Restaurant objRestaurant = this.restaurantMapper.fromRestaurant(dto);
		objRestaurant.referenceRestaurantLocation();
		restaurantPanacheRepository.persist(objRestaurant);
		emitterRestaurant.send(dto);
	}
	
	/**
	 * Read.
	 *
	 * @return the list
	 */
	@GET
	public List<RestaurantDTO> read() {
		return this.restaurantMapper.toRestaurantsDTO(
				restaurantPanacheRepository.list(
						"select r from Restaurant r "
						+ "	join fetch r.dishes "
						+ "	join fetch r.location"), new CycleAvoidingMappingContext());
	}
	
	/**
	 * Update.
	 *
	 * @param id the id
	 * @param dto the dto
	 */
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
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	@DELETE
	@Path("{id}")
	@Transactional
	public void delete(@PathParam("id") Long id) {
		if(!restaurantPanacheRepository.deleteById(id)) {
			throw new NotFoundException();
		}
	}
	
	/**
	 * Read dishes.
	 *
	 * @param idRestaurant the id restaurant
	 * @return the list
	 */
	@GET
	@Path("{idRestaurant}/dishes")
	@Transactional
	@Tag(name="Dishes Resources")
	public List<DishDTO> readDishes(@PathParam("idRestaurant") Long idRestaurant) {
		Optional<Restaurant> restaurantOp = restaurantPanacheRepository.findByIdOptional(idRestaurant);

		if(restaurantOp.isEmpty()) {
			throw new NotFoundException("Restaurant Not Found");
		}
		
		return this.dishMapper.toDishesDtos(restaurantOp.get().getDishes(), new CycleAvoidingMappingContext());
	}
	
	/**
	 * Creates the dish.
	 *
	 * @param idRestaurant the id restaurant
	 * @param dto the dto
	 */
	@POST
	@Path("{idRestaurant}/dishes")
	@Transactional
	@Tag(name="Dishes Resources")
	public void createDish(@PathParam("idRestaurant") Long idRestaurant, DishDTO dto) {
		Optional<Restaurant> restaurantOp = restaurantPanacheRepository.findByIdOptional(idRestaurant);

		if(restaurantOp.isEmpty()) {
			throw new NotFoundException("Restaurant Not Found");
		}
		
		Dish objDish = dishMapper.toDish(dto, new CycleAvoidingMappingContext());
		
		objDish.setRestaurant(restaurantOp.get());
		this.dishPanacheRepository.persist(objDish);
		
	}
	
	/**
	 * Delete dish.
	 *
	 * @param idRestaurant the id restaurant
	 * @param idDish the id dish
	 */
	@DELETE
	@Path("{idRestaurant}/dishes/{id}")
	@Transactional
	@Tag(name="Dishes Resources")
	public void deleteDish(@PathParam("idRestaurant") Long idRestaurant, @PathParam("id") Long idDish) {
		if(!dishPanacheRepository.deleteById(idDish)) {
			throw new NotFoundException("Dish Not Found");
		}
	}
	
	
	/**
	 * Update dish.
	 *
	 * @param idRestaurant the id restaurant
	 * @param idDish the id dish
	 * @param dto the dto
	 */
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
