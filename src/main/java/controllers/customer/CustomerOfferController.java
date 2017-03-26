
package controllers.customer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ApplicationService;
import services.OfferService;
import controllers.AbstractController;
import domain.Application;
import domain.Customer;
import domain.Offer;

@Controller
@RequestMapping("customer/offer/")
public class CustomerOfferController extends AbstractController {

	//Services

	@Autowired
	private OfferService		offerService;

	@Autowired
	private LoginService		loginService;

	@Autowired
	private ApplicationService	applicationService;


	@RequestMapping(value = "/apply")
	public ModelAndView apply(@RequestParam final int offerId) {
		ModelAndView res = null;
		final Offer o = this.offerService.findOne(offerId);
		Assert.notNull(o);
		Assert.isTrue(o.getBanned() == false);
		final Application a = new Application();
		a.setState("PENDING");
		a.setCustomer((Customer) this.loginService.getPrincipalActor());
		a.setDemand(o);
		this.applicationService.save(a);
		res = new ModelAndView("redirect:../../actor/offer/list.do");
		return res;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Offer offer = offerService.create();
		offer.setCustomer((Customer) loginService.getPrincipalActor());
		//socialIdentity.setActor(loginService.getPrincipalActor());
		result = createEditModelAndView(offer);
		return result;
	}

	//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	//	public ModelAndView create3(@RequestParam int offerId) {
	//		ModelAndView result;
	//		Offer offer = offerService.findOne(offerId);
	//		Assert.isTrue(offer.getCustomer().getId() == (loginService.getPrincipalActor().getId()));
	//		result = createEditModelAndView(offer);
	//		//result.addObject("s", socialIdentity);
	//		return result;
	//	}
	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save2(@Valid Offer offer, BindingResult binding) {
		ModelAndView result;
		Assert.isTrue(offer.getCustomer().getId() == (loginService.getPrincipalActor().getId()));
		if (binding.hasErrors()) {
			result = createEditModelAndView(offer);
		} else {
			try {
				offerService.save(offer);
				result = new ModelAndView("redirect:/actor/offer/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(offer, "commit.error");
			}
		}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Offer offer) {
		ModelAndView result;

		result = createEditModelAndView(offer, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Offer offer, String message) {
		ModelAndView result;
		result = new ModelAndView("customer/offer/edit");
		result.addObject("offer", offer);
		result.addObject("message", message);

		return result;
	}
}
