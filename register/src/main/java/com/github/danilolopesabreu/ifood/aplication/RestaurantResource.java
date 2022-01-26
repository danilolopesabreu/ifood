package com.github.danilolopesabreu.ifood.aplication;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;
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

import com.github.danilolopesabreu.ifood.domain.register.Restaurant;
import com.github.danilolopesabreu.ifood.infrastructure.register.RestaurantPanacheRepository;

@Path("/restaurants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestaurantResource {

	@Inject
	private RestaurantPanacheRepository restaurantPanacheRepository;
	
	@POST
	@Transactional
	public void create(Restaurant dto) {
		restaurantPanacheRepository.persist(dto);
	}
	
	@GET
	public List<Restaurant> read() {
		return restaurantPanacheRepository.listAll();
	}
	
	@PUT
	@Path("{id}")
	@Transactional
	public void update(@PathParam("id") Long id, Restaurant dto) {
		Optional<Restaurant> restaurantOp = restaurantPanacheRepository.findByIdOptional(id);
		
		if(restaurantOp.isEmpty()) {
			throw new NotFoundException();
		}
		
		Restaurant objRestaurant = restaurantOp.get();		
		
		objRestaurant.setName(dto.getName());
		
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
	
}
