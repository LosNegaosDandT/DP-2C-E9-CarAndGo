
package controllers.actor;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import controllers.AbstractController;
import domain.Actor;
import domain.Comment;

@Controller
@RequestMapping("/actor/comment")
public class ActorCommentController extends AbstractController {

	// Services ---------------------------------------------------------------

	//	@Autowired
	//	private LoginService	loginService;
	//	@Autowired
	//	private CommentService	commentService;
	@Autowired
	private ActorService	actorService;


	// Constructors -----------------------------------------------------------

	public ActorCommentController() {
		super();
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/actor")
	public ModelAndView commentActor(@RequestParam final int actorId) {
		ModelAndView result;
		result = new ModelAndView("comment/actor");
		//final List<Actor> aux = (List<Actor>) this.actorService.findAll();
		//Assert.notNull(aux);
		//Assert.notEmpty(aux);
		final Comment comment = new Comment();
		//			result.addObject("actor", r);
		result.addObject("comment", comment);
		return result;
	}
	@RequestMapping(value = "/actor", method = RequestMethod.POST, params = "save")
	public ModelAndView saveActor(@Valid final Comment comment, final BindingResult binding, final Integer actorId) {
		ModelAndView result;
		if (binding.hasErrors())
			result = new ModelAndView("comment/actor");
		else
			try {
				comment.setMoment(new Date());
				final Actor owner = this.actorService.findOne(actorId);
				final List<Comment> comments = (List<Comment>) owner.getComments();
				comments.add(comment);
				owner.setComments(comments);
				this.actorService.save(owner);
				result = new ModelAndView("redirect:../../actor/profile/details.do?actorId=" + actorId);
			} catch (final Throwable oops) {
				result = new ModelAndView("comment/actor");
				result.addObject("message", "commit.error");
			}
		return result;
	}

	//	@RequestMapping(value = "/request")
	//	public ModelAndView commentRequest() {
	//		ModelAndView result;
	//		result = new ModelAndView("comment/request");
	//		final List<Actor> aux = (List<Actor>) this.actorService.findAll();
	//		Assert.notNull(aux);
	//		Assert.notEmpty(aux);
	//		final Comment comment = new Comment();
	//		//			result.addObject("actor", r);
	//		result.addObject("actors", aux);
	//		result.addObject("comment", comment);
	//		return result;
	//	}
}
