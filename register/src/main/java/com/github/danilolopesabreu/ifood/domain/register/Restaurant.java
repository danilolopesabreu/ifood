package com.github.danilolopesabreu.ifood.domain.register;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "restaurant")
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String owner;
	
	public String cnpj;
	
	public String name;
	
	@OneToOne
	public Location location;
	
	@OneToMany
	public List<Dish> dishes;
	
	@CreationTimestamp
	public Date creationDate;
	
	@UpdateTimestamp
	public Date updateDate;
}
