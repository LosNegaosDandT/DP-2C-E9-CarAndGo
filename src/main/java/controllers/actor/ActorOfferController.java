
package controllers.actor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.OfferService;
import controllers.AbstractController;
import domain.Offer;

@Controller
@RequestMapping("/actor/offer")
public class ActorOfferController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private OfferService	offerService;


	// Constructors -----------------------------------------------------------

	public ActorOfferController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final String search) {
		ModelAndView result;
		Collection<Offer> offers;

		offers = this.offerService.searchRequests(search);

		result = new ModelAndView("actor/offer/list");
		result.addObject("offers", offers);

		return result;
	}

}
