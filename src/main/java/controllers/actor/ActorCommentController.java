
package controllers.actor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.CommentService;
import services.CustomerService;
import services.OfferService;
import services.RequestService;
import controllers.AbstractController;
import domain.Administrator;
import domain.Comment;
import domain.Customer;
import domain.Offer;
import domain.Request;
import forms.CommentForm;

@Controller
@RequestMapping("/actor/comment")
public class ActorCommentController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private CustomerService			customerService;
	@Autowired
	private AdministratorService	adminService;
	@Autowired
	private CommentService			commentService;
	@Autowired
	private RequestService			requestService;
	@Autowired
	private OfferService			offerService;


	// Constructors -----------------------------------------------------------

	public ActorCommentController() {
		super();
	}

	// Commentar actor ---------------------------------------------------------------

	@RequestMapping(value = "/actor", method = RequestMethod.GET)
	public ModelAndView commentActor(@RequestParam final int actorId) {
		final ModelAndView res = new ModelAndView("comment/actor");
		final CommentForm commentForm = new CommentForm();
		commentForm.setId(actorId);
		res.addObject("requestURI", "actor/comment/actor.do");
		res.addObject("commentForm", commentForm);
		return res;
	}

	@RequestMapping(value = "/actor", method = RequestMethod.POST, params = "save")
	public ModelAndView commentActor(@Valid final CommentForm commentForm, final BindingResult bindingResult) {

		ModelAndView res = new ModelAndView("comment/actor");
		Comment comment = this.commentService.create();
		comment = this.commentService.reconstruct(commentForm, comment);

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			res.addObject("requestURI", "actor/comment/actor.do");
			res.addObject("commentForm", commentForm);
		} else
			try {
				if (this.customerService.findOne(commentForm.getId()) != null) {
					final Customer customer = this.customerService.findOne(commentForm.getId());
					customer.getComments().add(comment);
					this.customerService.save(customer);
					this.commentService.save(comment);
					res = new ModelAndView("redirect:/actor/profile/customer.do?actorId=" + commentForm.getId());
				} else {
					final Administrator admin = this.adminService.findOne(commentForm.getId());
					admin.getComments().add(comment);
					this.adminService.save(admin);
					this.commentService.save(comment);
					res = new ModelAndView("redirect:/actor/profile/admin.do?actorId=" + commentForm.getId());
				}
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());
				res.addObject("message", "commit.error");
			}
		return res;
	}

	// Commentar demand ---------------------------------------------------------------

	@RequestMapping(value = "/demand", method = RequestMethod.GET)
	public ModelAndView commentDemand(@RequestParam final int demandId) {
		final ModelAndView res = new ModelAndView("comment/demand");
		final CommentForm commentForm = new CommentForm();
		commentForm.setId(demandId);
		res.addObject("requestURI", "actor/comment/demand.do");
		res.addObject("commentForm", commentForm);
		return res;
	}

	@RequestMapping(value = "/demand", method = RequestMethod.POST, params = "save")
	public ModelAndView commentDemand(@Valid final CommentForm commentForm, final BindingResult bindingResult) {

		ModelAndView res = new ModelAndView("comment/demand");
		Comment comment = this.commentService.create();
		comment = this.commentService.reconstruct(commentForm, comment);

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			res.addObject("requestURI", "actor/comment/demand.do");
			res.addObject("commentForm", commentForm);
		} else
			try {
				if (this.requestService.findOne(commentForm.getId()) != null) {
					final Request request = this.requestService.findOne(commentForm.getId());
					request.getComments().add(comment);
					this.requestService.save(request);
					this.commentService.save(comment);
					res = new ModelAndView("redirect:/actor/demand/details/request.do?demandId=" + commentForm.getId());
				} else {
					final Offer offer = this.offerService.findOne(commentForm.getId());
					offer.getComments().add(comment);
					this.offerService.save(offer);
					this.commentService.save(comment);
					res = new ModelAndView("redirect:/actor/demand/details/offer.do?demandId=" + commentForm.getId());
				}
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());
				res.addObject("message", "commit.error");
			}
		return res;
	}

}
