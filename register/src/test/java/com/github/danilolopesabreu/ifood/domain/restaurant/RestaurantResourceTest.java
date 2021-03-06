package com.github.danilolopesabreu.ifood.domain.restaurant;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.danilolopesabreu.ifood.infrastructure.DataBaseLifeCycle;
import com.github.danilolopesabreu.ifood.infrastructure.TokenUtils;
import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(DataBaseLifeCycle.class)
class RestaurantResourceTest {

	private String token;

	@BeforeEach
	private void generateToken() throws Exception {
		this.token = TokenUtils.generateTokenString("/jwt/JWTPropertyClaims.json", null);
	}
	
	private RequestSpecification given() {
		return RestAssured.given().contentType(ContentType.JSON)
					.header(new Header("Authorization", "Bearer "+token));
	}
	
	@Test
	@DataSet(value = "restaurant.yml")
	void testListRestaurants() {
		String result = 
					given()
						.when().get("/restaurants")
						.then().statusCode(200)
						.extract().asString();
		
		Approvals.verifyJson(result);
	}
	
	@Test
	void testCreateRestaurant() {
		
//		RestaurantDTO newRestaurant = new RestaurantDTO("Mr. Smith","66","Sushi Bar", new LocationDTO(6669D, 6688D));
		
		String result = 
					given()
						.when().get("/restaurants")
						.then().statusCode(200)
						.extract().asString();
		
		Approvals.verifyJson(result);
	}

}
