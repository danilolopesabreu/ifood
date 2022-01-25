package com.github.danilolopesabreu.ifood.infrastructure.register;

import javax.enterprise.context.ApplicationScoped;

import com.github.danilolopesabreu.ifood.domain.register.Restaurant;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class RestaurantPanacheRepository implements PanacheRepository<Restaurant>{

}
