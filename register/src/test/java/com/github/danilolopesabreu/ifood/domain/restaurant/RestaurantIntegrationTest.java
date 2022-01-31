package com.github.danilolopesabreu.ifood.domain.restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import com.github.danilolopesabreu.ifood.infrastructure.DataBaseLifeCycle;
import com.github.danilolopesabreu.ifood.infrastructure.restaurant.RestaurantPanacheRepository;
import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(DataBaseLifeCycle.class)
class RestaurantIntegrationTest {

	@Inject
	protected RestaurantPanacheRepository restaurantPanacheRepository;
	
	@Test
	@DataSet(
			value = "restaurant.yml"
		)
	void existsOnlyOneRestaurant() {
		assertEquals(1, restaurantPanacheRepository.count());
	}

}
