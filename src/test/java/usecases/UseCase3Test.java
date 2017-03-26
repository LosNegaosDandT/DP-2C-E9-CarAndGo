
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

	/*
	 * An actor who is authenticated as a customer must be able to:
	 * Post a request in which he or she informs that he or she
	 * wishes to move from a place to another one and would like
	 * to find someone with whom he or she can share the trip.
	 */

	// System under test ------------------------------------------------------
	@Autowired
	private RequestService	requestService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driver() {
		final Object testingData[][] = {
			{
				//Un customer se loguea correctamente y publica una request correctamente.
				"customer", "titulo", "esta es la descripcion", "Lora del rio", null, null, "Alcolea del rio", null, null, new Date(), null
			}, {
				//Un usuario sin loguear intenta publicar una request.
				null, "titulo", "descipcion", "Lora del rio", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), IllegalArgumentException.class
			}, {
				//Un admin intenta publicar una request.
				"admin", "titulo", "descipcion", "Lora del rio", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), IllegalArgumentException.class
			}, {
				//Un customer se loguea correctamente y publica una request correctamente.
				"customer2", "titulo", "descipcion", "Lora del rio", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), null
			}, {
				//Un customer se loguea correctamente y publica una request correctamente.
				"customer3", "titulo", "descipcion", "Lora del rio", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), null
			}, {
				//Un customer se loguea correctamente y publica una request sin descripcion.
				"customer", "titulo", "", "Lora del rio", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), ConstraintViolationException.class
			}, {
				//Un customer se loguea correctamente y publica una request sin titulo.
				"customer", "", "descipcion", "Lora del rio", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), ConstraintViolationException.class
			}, {
				//Un customer se loguea correctamente y publica una request sin titulo ni descripcion.
				"customer", "", "", "Lora del rio", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), ConstraintViolationException.class
			}, {
				//Un customer se loguea correctamente y publica una request con la Address de origen vacía.
				"customer", "titulo", "descipcion", "", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), ConstraintViolationException.class
			}, {
				//Un customer se loguea correctamente y publica una request con la Address destino vacía.
				"customer", "titulo", "descipcion", "Lora del rio", 22.2, 23.7, "", 34.5, 45.3, new Date(), ConstraintViolationException.class
			}, {
				//Un customer se loguea correctamente y publica una request sin fecha.
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
