package com.goodweatherjpa.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NwsEndpointTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("GoodWeatherFinderJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}
	
	@Test
	@DisplayName("Database Connection")
	void test_DB_connection() {
		assertNotNull(em);
	}
	
	@Test
	@DisplayName("Find All NwsEndpoints")
	void test_all_NwsEndpoints() {
		@SuppressWarnings("unchecked")
		List<NwsEndpoint> nwsEndpoints = em.createQuery("from NwsEndpoint").getResultList();
		assertNotNull(nwsEndpoints);
		assertTrue(nwsEndpoints.size() > 0);
		
		// verify NwsEndpoint
		for (NwsEndpoint nwsEndpoint : nwsEndpoints) {
			assertNotNull(nwsEndpoint);
			assertNotNull(nwsEndpoint.getType());
			assertNotNull(nwsEndpoint.getEndpoint());
		}
		
	}

}
