package com.github.danilolopesabreu.ifood.infrastructure.restaurant;

import javax.enterprise.context.ApplicationScoped;

import com.github.danilolopesabreu.ifood.domain.restaurant.Dish;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class DishPanacheRepository implements PanacheRepository<Dish>{

}
