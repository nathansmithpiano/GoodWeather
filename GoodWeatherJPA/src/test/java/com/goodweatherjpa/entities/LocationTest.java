package com.goodweatherjpa.entities;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

class LocationTest {

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
	@DisplayName("All Locations")
	void test_all_Locations() {
		
		// Obtain all Locations as List
		@SuppressWarnings("unchecked")
		List<Location> locations = (List<Location>) em.createQuery("from Location").getResultList();
		assertNotNull(locations);
		assertTrue(locations.size() > 0);
		
		// verify Location and Coordinates
		for (Location location: locations) {
			assertNotNull(location);
			assertNotNull(location.getName());
			assertNotNull(location.getCoordinates());
			double latitude = location.getCoordinates().getLatitude();
			assertTrue(latitude != 0);
			double longitude = location.getCoordinates().getLatitude();
			assertTrue(longitude != 0);
		}
	}
	
}
