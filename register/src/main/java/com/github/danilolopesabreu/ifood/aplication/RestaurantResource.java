package com.github.danilolopesabreu.ifood.aplication;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
	
	@GET
	public List<Restaurant> listRestaurants() {
		return restaurantPanacheRepository.listAll();
	} 
}
