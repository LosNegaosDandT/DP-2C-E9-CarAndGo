/*
 * SampleTest.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RequestServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private RequestService	requestService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driverCreate() {
		final Object testingData[][] = {
			{
				"customer", null
			}, {
				null, IllegalArgumentException.class
			}, {
				"admin", IllegalArgumentException.class
			},

		};
		for (int i = 0; i < testingData.length; i++)
			this.templateCreate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateCreate(final String surname, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(surname);
			this.requestService.create();
			this.unauthenticate();
			this.requestService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
