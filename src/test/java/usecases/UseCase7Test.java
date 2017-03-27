
package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.OfferService;
import services.RequestService;
import utilities.AbstractTest;
import domain.Offer;
import domain.Request;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase7Test extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private OfferService	offerService;

	@Autowired
	private RequestService	requestService;


	// Tests ------------------------------------------------------------------

	/*
	 * An actor who is authenticated as an administrator must be able to:
	 * 
	 * Ban an offer or a request that he or she finds inappropriate.
	 * Such offers and re-quests must not be displayed to a general audience,
	 * only to the administrators and the customer who posted it.
	 */

	@Test
	public void driver() {
		final Object testingData[][] = {
			{
				//El admin banea la request con id 89
				"admin", null, 89, true, null
			}, {
				//Un usuario sin loguear intenta banear una request.
				"null", null, 90, true, IllegalArgumentException.class
			}, {
				//El admin banea la offer con id 89
				"admin", 82, null, true, null
			}, {
				//Un usuario sin loguear intenta banear una offer.
				"null", 83, null, true, IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (Integer) testingData[i][1], (Integer) testingData[i][2], (Boolean) testingData[i][3], (Class<?>) testingData[i][4]);
	}

	// Ancillary methods ------------------------------------------------------

	protected void template(final String username, final Integer offerId, final Integer requestId, final Boolean ban, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			//Autenticación
			this.authenticate(username);
			//Meto datos
			if (offerId == null) {
				final Request r = this.requestService.findOne(requestId);
				r.setBanned(ban);
				this.requestService.save(r);
			} else if (requestId == null) {
				final Offer o = this.offerService.findOne(offerId);
				o.setBanned(ban);
				this.offerService.save(o);
			}
			//Logout
			this.unauthenticate();
			this.requestService.flush();
			this.offerService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
			System.out.println(oops.getClass());
		}
		this.checkExceptions(expected, caught);
	}

}
