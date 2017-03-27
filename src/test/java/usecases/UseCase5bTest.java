
package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.RequestService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase5bTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private RequestService	requestService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driver() {
		final Object testingData[][] = {
			{
				null, "Hla", IllegalArgumentException.class
			}, {
				"customer", "Sevilla", null
			}, {
				"admin", "Sevilla", null
			}, {
				"customer2", "Sevilla", null
			}, {
				"pokahontas", "Sevilla", IllegalArgumentException.class
			}, {
				null, "Cordoba", IllegalArgumentException.class
			}, {
				"customer", "ZanLuca", null
			}, {
				"admin", "Cordoba", null
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	// Ancillary methods ------------------------------------------------------

	protected void template(final String username, final String search, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			this.requestService.searchRequests(search);
			this.unauthenticate();
			this.requestService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
