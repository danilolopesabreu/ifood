package com.github.danilolopesabreu.ifood.domain.restaurant;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import com.github.danilolopesabreu.ifood.infrastructure.DataBaseLifeCycle;
import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(DataBaseLifeCycle.class)
class RestaurantResourceTest {

	@Test
	@DataSet(value = "restaurant.yml")
	void testListRestaurants() {
		String result = 
				RestAssured
					.given()
						.when().get("/restaurants")
						.then().statusCode(200)
						.extract().asString();
		
		Approvals.verifyJson(result);
	}

}
