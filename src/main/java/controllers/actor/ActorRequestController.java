
package controllers.actor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RequestService;
import controllers.AbstractController;
import domain.Request;

@Controller
@RequestMapping("/actor/request")
public class ActorRequestController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private RequestService	requestService;


	// Constructors -----------------------------------------------------------

	public ActorRequestController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final String search) {
		ModelAndView result;
		Collection<Request> requests;

		requests = this.requestService.searchRequests(search);

		result = new ModelAndView("actor/request/list");
		result.addObject("requests", requests);

		return result;
	}

}
