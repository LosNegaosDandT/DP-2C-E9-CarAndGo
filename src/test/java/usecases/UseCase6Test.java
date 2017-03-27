
package usecases;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.BannerService;
import utilities.AbstractTest;
import domain.Banner;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase6Test extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private BannerService	bannerService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driver() {
		final Object testingData[][] = {
			{
				"admin", "http://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg", null
			}, {
				"customer", "estonoesunaurlxD", ConstraintViolationException.class
			}, {
				"Nousseeer", "estonoesunaurlxD", IllegalArgumentException.class
			}, {
				"jajajXDLOL", "http://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg", IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	// Ancillary methods ------------------------------------------------------

	protected void template(final String username, final String url, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			Collection<Banner> r;
			r = this.bannerService.findAll();
			final Banner b = ((List<Banner>) r).get(0);
			b.setImage(url);
			this.bannerService.save(b);
			this.unauthenticate();
			this.bannerService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
}
