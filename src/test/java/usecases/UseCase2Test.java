
package usecases;

import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.OfferService;
import utilities.AbstractTest;
import domain.Offer;
import domain.Place;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase2Test extends AbstractTest {

	/*
	 * An actor who is authenticated as a customer must be able to:
	 * Post an offer in which he or she advertises that he’s going to
	 * move from a place to another place and would like to share
	 * his or her car with someone else.
	 */

	// System under test ------------------------------------------------------
	@Autowired
	private OfferService	offerService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driver() {
		final Object testingData[][] = {
			{
				//Un customer se loguea correctamente y publica una Offer correctamente.
				"customer", "titulo", "esta es la descripcion", "Lora del rio", null, null, "Alcolea del rio", null, null, new Date(), null
			}, {
				//Un usuario sin loguear intenta publicar una Offer.
				null, "titulo", "descipcion", "Lora del rio", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), IllegalArgumentException.class
			}, {
				//Un Admin intenta publicar una Offer.
				"admin", "titulo", "descipcion", "Lora del rio", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), IllegalArgumentException.class
			}, {
				//Un customer se loguea correctamente y publica una Offer correctamente.
				"customer2", "titulo", "descipcion", "Lora del rio", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), null
			}, {
				//Un customer se loguea correctamente y publica una Offer correctamente.
				"customer3", "titulo", "descipcion", "Lora del rio", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), null
			}, {
				//Un customer se loguea correctamente y publica una Offer con una descripción vacía.
				"customer", "titulo", "", "Lora del rio", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), ConstraintViolationException.class
			}, {
				//Un customer se loguea correctamente y publica una Offer con un título vacío.
				"customer", "", "descipcion", "Lora del rio", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), ConstraintViolationException.class
			}, {
				//Un customer se loguea correctamente y publica una Offer con título y descripción vacíos.
				"customer", "", "", "Lora del rio", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), ConstraintViolationException.class
			}, {
				//Un customer se loguea correctamente y publica una Offer con la Address de origen vacía.
				"customer", "titulo", "descipcion", "", 22.2, 23.7, "Alcolea del rio", 34.5, 45.3, new Date(), ConstraintViolationException.class
			}, {
				//Un customer se loguea correctamente y publica una Offer con la Address destino vacía.
				"customer", "titulo", "descipcion", "Lora del rio", 22.2, 23.7, "", 34.5, 45.3, new Date(), ConstraintViolationException.class
			}, {
				//Un customer se loguea correctamente y publica una Offer sin fecha.
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
			final Offer o = this.offerService.create();
			//Meto datos a offer
			o.setDescription(description);
			o.setDestination(destination);
			o.setMoment(moment);
			o.setOrigin(origin);
			o.setTitle(title);
			//Guardo la offer
			this.offerService.save(o);
			//Logout
			this.unauthenticate();
			this.offerService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
