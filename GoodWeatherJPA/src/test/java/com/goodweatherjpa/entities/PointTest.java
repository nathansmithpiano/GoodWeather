package com.goodweatherjpa.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.goodweatherjpa.data.PointParser;

class PointTest {

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

//	@Test
//	@DisplayName("Find All Points")
//	void test_all_Coordinates() {
//		@SuppressWarnings("unchecked")
//		List<Point> points = em.createQuery("from Point").getResultList();
//		assertNotNull(points);
//		assertTrue(points.size() > 0);
//		System.out.println(points);
//	}

	@Test
	@DisplayName("Create Point for each Location")
	@SuppressWarnings("unchecked")
	void test_create_Points() {
		
		String query = null;
		
		// Obtain all Locations as List
		query = "from Location";
		List<Location> locations = (List<Location>) em.createQuery(query).getResultList();
		assertNotNull(locations);
		assertTrue(locations.size() > 0);
		
		// Find endpoint for Point
		query = "from NwsEndpoint WHERE type IS 'point'";
		NwsEndpoint nwsEndpoint = (NwsEndpoint) em.createQuery(query).getSingleResult();
		assertNotNull(nwsEndpoint);
		assertEquals("point", nwsEndpoint.getType());
		String endpoint = nwsEndpoint.getEndpoint();
		assertNotNull(endpoint);
		
		// Create Point for each Location and persist 
		PointParser parser = new PointParser();
		
		for (Location location: locations) {
			double latitude = location.getCoordinates().getLatitude();
			double longitude = location.getCoordinates().getLongitude();
			Point point = parser.parse(endpoint, latitude, longitude);
			assertNotNull(point);
			String uri = point.getUri();
			
			point.setLocation(location);
			
			// Persist
			em.getTransaction().begin();
			
			// Remove if already existing
			Point foundPoint = em.find(Point.class, point.getUri());
			
			if (foundPoint != null) {
				em.remove(foundPoint);
			}
			
			em.persist(point);
			
			em.getTransaction().commit();
			
			// Verify in DB and display
			foundPoint = em.find(Point.class, point.getUri());
			assertNotNull(foundPoint);
			
			// uri
			assertNotNull(foundPoint.getUri());
			assertEquals(uri, foundPoint.getUri());
			
			// location
			Location foundLocation = foundPoint.getLocation();
			assertNotNull(foundLocation);
			assertEquals(location.getName(), foundLocation.getName());
			
			assertEquals(latitude, foundPoint.getLocation().getCoordinates().getLatitude());
			assertEquals(longitude, foundPoint.getCoordinates().getLongitude());
			
			System.out.println(foundPoint);
		}

	}

}
