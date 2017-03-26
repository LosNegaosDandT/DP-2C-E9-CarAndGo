
package usecases;

import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.RequestService;
import utilities.AbstractTest;
import domain.Place;
import domain.Request;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase3Test extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private RequestService	requestService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driver() {
		final Object testingData[][] = {
			{
				"customer", "titulo", "esta es la descripcion", "Lora del rio", null, null, "Alcolea del rio", null, null, new Date(), null
			}, {
				null, "titulo", "descipcion", "Lora del rio", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), IllegalArgumentException.class
			}, {
				"admin", "titulo", "descipcion", "Lora del rio", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), IllegalArgumentException.class
			}, {
				"customer2", "titulo", "descipcion", "Lora del rio", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), null
			}, {
				"customer3", "titulo", "descipcion", "Lora del rio", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), null
			}, {
				"customer", "titulo", "", "Lora del rio", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), ConstraintViolationException.class
			}, {
				"customer", "", "descipcion", "Lora del rio", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), ConstraintViolationException.class
			}, {
				"customer", "", "", "Lora del rio", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), ConstraintViolationException.class
			}, {
				"customer", "titulo", "descipcion", "", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), ConstraintViolationException.class
			}, {
				"customer", "titulo", "descipcion", "Lora del rio", 22.2, 23.7, "", 34.5, 45.3, new Date(), ConstraintViolationException.class
			}, {
				"customer", "titulo", "descipcion", "Lora del rio", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, null, ConstraintViolationException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Double) testingData[i][4], (Double) testingData[i][5], (String) testingData[i][6], (Double) testingData[i][7],
				(Double) testingData[i][8], (Date) testingData[i][9], (Class<?>) testingData[i][10]);
	}

	// Ancillary methods ------------------------------------------------------

	protected void template(final String username, final String title, final String description, final String originAddress, final Double originLatitude, final Double originLongitude, final String destinationAddress, final Double destinationLatitude,
		final Double destinationLongitude, final Date moment, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			final Place origin = new Place();
			origin.setAddress(originAddress);
			origin.setLatitude(originLatitude);
			origin.setLongitude(originLongitude);
			final Place destination = new Place();
			destination.setAddress(destinationAddress);
			destination.setLatitude(destinationLatitude);
			destination.setLongitude(destinationLongitude);
			//Autenticación
			this.authenticate(username);
			//Creacion de la offer
			final Request o = this.requestService.create();
			//Meto datos a offer
			o.setDescription(description);
			o.setDestination(destination);
			o.setMoment(moment);
			o.setOrigin(origin);
			o.setTitle(title);
			//Guardo la offer
			this.requestService.save(o);
			//Logout
			this.unauthenticate();
			this.requestService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
