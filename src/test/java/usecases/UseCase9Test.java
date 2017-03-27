
package usecases;

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
public class UseCase9Test extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private CommentService	commentService;


	// Tests ------------------------------------------------------------------

	/*
	 * An actor who is authenticated as an administrator must be able to:
	 * 
	 * Ban a comment that he or she finds inappropriate. Such comments
	 * must not be displayed to a general audience, only to the
	 * administrators and the actor who post-ed it.
	 */

	@Test
	public void driver() {
		final Object testingData[][] = {
			{
				//El admin banea el comentario con id 69
				"admin", 69, true, null
			}, {
				//Un usuario sin loguear intenta banear un comentario.
				"null", 70, true, IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (Integer) testingData[i][1], (Boolean) testingData[i][2], (Class<?>) testingData[i][3]);
	}

	// Ancillary methods ------------------------------------------------------

	protected void template(final String username, final Integer commentId, final Boolean ban, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			//Autenticación
			this.authenticate(username);
			//Meto datos
			final Comment r = this.commentService.findOne(commentId);
			r.setBanned(ban);
			this.commentService.save(r);
			//Logout
			this.unauthenticate();
			this.commentService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
			System.out.println(oops.getClass());
		}
		this.checkExceptions(expected, caught);
	}

}
