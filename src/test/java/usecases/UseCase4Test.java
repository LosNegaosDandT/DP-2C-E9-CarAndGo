
package usecases;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.ApplicationService;
import services.CustomerService;
import services.OfferService;
import services.RequestService;
import utilities.AbstractTest;
import domain.Application;
import domain.Customer;
import domain.Offer;
import domain.Request;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase4Test extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private OfferService		offerService;

	@Autowired
	private RequestService		requestService;


	// Tests ------------------------------------------------------------------

	//	En los dos primeros casos de uso hemos introducido parámetros correctos, por lo que no se produce ningún tipo de excepción.
	//	En el tercer caso de uso hemos introducido un cliente que no existe, lo cual produce una excepción.
	//	En el cuarto caso de uso hemos dejado el parámetro "state" vacío, lo cual produce una excepción.
	//	En el quinto caso de uso hemos dejado el parámetro "username" vacío, lo cual produce una excepción.
	//	En el sexto caso de uso hemos dejado tanto offerId como requestId vacíos, por lo que al no haber una demand asociada se produce una excepción.
	//	En el último caso de uso hemos dejado el parámetro "customerId" vacío, por lo que al no tener un cliente asociado se produce una excepción.

	@Test
	public void driver() {
		final Object testingData[][] = {
			{
				"customer", 75, 78, null, "DENIED", null
			}, {
				"customer", 75, null, 91, "ACCEPTED", null
			}, {
				"customer", 3000, null, 91, "ACCEPTED", ConstraintViolationException.class
			}, {
				"customer", 75, null, 91, "", ConstraintViolationException.class
			}, {
				"", 75, null, 91, "", IllegalArgumentException.class
			}, {
				"customer", 75, null, null, "ACCEPTED", NullPointerException.class
			}, {
				"customer", null, null, 91, "DENIED", NullPointerException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (Integer) testingData[i][1], (Integer) testingData[i][2], (Integer) testingData[i][3], (String) testingData[i][4], (Class<?>) testingData[i][5]);
	}

	// Ancillary methods ------------------------------------------------------

	protected void template(final String username, final Integer customerId, final Integer offerId, final Integer requestId, final String state, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			final Customer c = this.customerService.findOne(customerId);

			//Autenticación
			this.authenticate(username);
			//Creacion de la applicatiion
			final Application a = this.applicationService.create();
			//Meto datos a application
			a.setState(state);
			a.setCustomer(c);
			if (offerId == null) {
				final Request r = this.requestService.findOne(requestId);
				a.setDemand(r);
			} else if (requestId == null) {
				final Offer o = this.offerService.findOne(offerId);
				a.setDemand(o);
			}

			//Guardo la application
			this.applicationService.save(a);
			//Logout
			this.unauthenticate();
			this.applicationService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
			System.out.println(oops.getClass());
		}
		this.checkExceptions(expected, caught);
	}

}
