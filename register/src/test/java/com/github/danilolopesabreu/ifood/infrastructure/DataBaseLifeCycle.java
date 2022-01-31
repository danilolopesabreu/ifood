package com.github.danilolopesabreu.ifood.infrastructure;

import java.util.HashMap;
import java.util.Map;

import org.testcontainers.containers.PostgreSQLContainer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class DataBaseLifeCycle implements QuarkusTestResourceLifecycleManager {

	private static PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:12.2");
	
	@Override
	public Map<String, String> start() {
		POSTGRES.start();
		
		final Map<String, String> proprerties = new HashMap<String, String>();
		
		proprerties.put("quarkus.datasource.jdbc.url", POSTGRES.getJdbcUrl());
		proprerties.put("quarkus.datasource.username", POSTGRES.getUsername());
		proprerties.put("quarkus.datasource.password", POSTGRES.getPassword());
		
		return proprerties;
	}

	@Override
	public void stop() {
		if(POSTGRES != null && POSTGRES.isRunning())
			POSTGRES.stop();
	}

}
