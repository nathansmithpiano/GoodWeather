package com.goodweatherjpa.dbtests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DBTests {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	private int numEntities = 1000000;
	private int numToRetrieve = 100000;

	private final String stringId = "https://temp.resource.loc/type/stringentity/";
	private final String contents = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam a dignissim ipsum. Fusce malesuada accumsan condimentum. In felis risus, sagittis molestie mauris in, ultricies tristique quam. Phasellus molestie ligula tellus, vitae facilisis erat malesuada in. Praesent id ex lacinia, semper ligula non, egestas libero. Nam ut diam a tellus gravida cursus. Sed convallis massa non metus pharetra efficitur. Sed efficitur dignissim felis porttitor egestas.";

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

	/*
	 * @Test
	 * 
	 * @DisplayName("Create test entities") void create_test_entities() {
	 * 
	 * // RESULTS FROM 7/19/22 11:34AM // TIME TO CREATE INT ENTITIES: 00:03:06 //
	 * TIME TO CREATE STRING ENTITIES: 00:03:16 // TIME DIFFERENCE: 00:00:09
	 * 
	 * // IntEntity LocalDateTime startTime = LocalDateTime.now();
	 * 
	 * em.getTransaction().begin();
	 * 
	 * for (int i = 0; i < numEntities; i++) {
	 * 
	 * IntEntity entity = new IntEntity(); entity.setContents(contents);
	 * 
	 * em.persist(entity); }
	 * 
	 * em.getTransaction().commit();
	 * 
	 * LocalDateTime endTime = LocalDateTime.now();
	 * 
	 * Duration intDur = Duration.between(startTime, endTime); long intMillis =
	 * intDur.toMillis();
	 * 
	 * String duration = String.format("%02d:%02d:%02d",
	 * TimeUnit.MILLISECONDS.toHours(intMillis),
	 * TimeUnit.MILLISECONDS.toMinutes(intMillis) -
	 * TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(intMillis)),
	 * TimeUnit.MILLISECONDS.toSeconds(intMillis) -
	 * TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(intMillis)));
	 * 
	 * System.out.println("TIME TO CREATE INT ENTITIES: " + duration);
	 * 
	 * // StringEntity startTime = LocalDateTime.now();
	 * 
	 * em.getTransaction().begin();
	 * 
	 * for (int i = 0; i < numEntities; i++) {
	 * 
	 * StringEntity entity = new StringEntity(); entity.setContents(contents);
	 * 
	 * entity.setId(stringId + i);
	 * 
	 * em.persist(entity); }
	 * 
	 * em.getTransaction().commit();
	 * 
	 * endTime = LocalDateTime.now();
	 * 
	 * Duration stringDur = Duration.between(startTime, endTime); long stringMillis
	 * = stringDur.toMillis();
	 * 
	 * duration = String.format("%02d:%02d:%02d",
	 * TimeUnit.MILLISECONDS.toHours(stringMillis),
	 * TimeUnit.MILLISECONDS.toMinutes(stringMillis) -
	 * TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(stringMillis)),
	 * TimeUnit.MILLISECONDS.toSeconds(stringMillis) -
	 * TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(stringMillis)));
	 * 
	 * System.out.println("TIME TO CREATE STRING ENTITIES: " + duration);
	 * 
	 * long differenceMillis = stringMillis - intMillis; differenceMillis =
	 * Math.abs(differenceMillis);
	 * 
	 * duration = String.format("%02d:%02d:%02d",
	 * TimeUnit.MILLISECONDS.toHours(differenceMillis),
	 * TimeUnit.MILLISECONDS.toMinutes(differenceMillis) -
	 * TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(differenceMillis)),
	 * TimeUnit.MILLISECONDS.toSeconds(differenceMillis) -
	 * TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(differenceMillis))
	 * );
	 * 
	 * System.out.println("TIME DIFFERENCE: " + duration);
	 * 
	 * }
	 */

	@Test
	@DisplayName("Retrieve test entities")
	void retrieve_test_entities() {

		Set<Integer> idsToRetrieve = new HashSet<>();

		for (int i = 0; i < numToRetrieve; i++) {
			Random random = new Random();
			int id = random.nextInt(numEntities);

			while (idsToRetrieve.contains(id)) {
				id = random.nextInt(numEntities);
			}

			idsToRetrieve.add(id);
		}

		assertEquals(numToRetrieve, idsToRetrieve.size());

		Set<IntEntity> intEntities = new HashSet<>();

		LocalDateTime startTime = LocalDateTime.now();

		for (int id : idsToRetrieve) {

			// IntEntities
			IntEntity intEntity = em.find(IntEntity.class, id + 1);
			assertNotNull(intEntity);
			assertEquals(id + 1, intEntity.getId());
			intEntities.add(intEntity);

		}

		assertEquals(numToRetrieve, intEntities.size());

		LocalDateTime endTime = LocalDateTime.now();

		Duration intDur = Duration.between(startTime, endTime);
		long intMillis = intDur.toMillis();

		String duration = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(intMillis),
				TimeUnit.MILLISECONDS.toMinutes(intMillis)
						- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(intMillis)),
				TimeUnit.MILLISECONDS.toSeconds(intMillis)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(intMillis)));

		System.out.println("TIME TO RETRIEVE INT ENTITIES: " + duration);

		Set<StringEntity> stringEntities = new HashSet<>();

		startTime = LocalDateTime.now();

		for (int id : idsToRetrieve) {

			// StringEntities
			StringEntity stringEntity = em.find(StringEntity.class, stringId + id);
			assertNotNull(stringEntity);
			assertEquals(stringId + id, stringEntity.getId());
			stringEntities.add(stringEntity);
		}

		assertEquals(numToRetrieve, stringEntities.size());

		endTime = LocalDateTime.now();

		Duration stringDur = Duration.between(startTime, endTime);
		long stringMillis = stringDur.toMillis();

		duration = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(stringMillis),
				TimeUnit.MILLISECONDS.toMinutes(stringMillis)
						- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(stringMillis)),
				TimeUnit.MILLISECONDS.toSeconds(stringMillis)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(stringMillis)));

		System.out.println("TIME TO RETRIEVE STRING ENTITIES: " + duration);

		long differenceMillis = stringMillis - intMillis;
		differenceMillis = Math.abs(differenceMillis);

		duration = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(differenceMillis),
				TimeUnit.MILLISECONDS.toMinutes(differenceMillis)
						- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(differenceMillis)),
				TimeUnit.MILLISECONDS.toSeconds(differenceMillis)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(differenceMillis)));

		System.out.println("TIME DIFFERENCE: " + duration);

	}

}
