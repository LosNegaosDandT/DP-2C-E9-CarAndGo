
package usecases;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.CommentService;
import utilities.AbstractTest;
import domain.Comment;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase8Test extends AbstractTest {

	/*
	 * An actor who is authenticated must be able to:
	 * o Post a comment on another actor, on an offer, or a request
	 */

	// System under test ------------------------------------------------------

	@Autowired
	private CommentService	commentService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driver() {
		final Object testingData[][] = {
			{
				//Customer postea un comentario correctamente.
				"customer", "la ramona", "esta es la descripcion", 5, new Date(), true, null
			}, {
				//Admin postea un comentario correctamente.
				"admin", "la ramona", "esta es la descripcion", 5, new Date(), true, null
			}, {
				//Usuario no logueado intenta postear un comentario.
				"null", "la ramona", "esta es la descripcion", 5, new Date(), true, IllegalArgumentException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Integer) testingData[i][3], (Date) testingData[i][4], (Boolean) testingData[i][5], (Class<?>) testingData[i][6]);
	}

	// Ancillary methods ------------------------------------------------------

	protected void template(final String username, final String title, final String text, final Integer stars, final Date moment, final Boolean banned, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			//Autenticación
			this.authenticate(username);
			//Creacion del comentario
			final Comment c = this.commentService.create();
			//Meto datos al comentario
			c.setTitle(title);
			c.setText(text);
			c.setStars(stars);
			c.setMoment(moment);
			c.setBanned(banned);
			//Guardo el comentario
			this.commentService.save(c);
			//Logout
			this.unauthenticate();
			this.commentService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
