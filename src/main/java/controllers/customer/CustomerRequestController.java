
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
import services.RequestService;
import controllers.AbstractController;
import domain.Application;
import domain.Customer;
import domain.Request;

@Controller
@RequestMapping("customer/request/")
public class CustomerRequestController extends AbstractController {

	//Services

	@Autowired
	private RequestService		requestService;

	@Autowired
	private LoginService		loginService;

	@Autowired
	private ApplicationService	applicationService;


	@RequestMapping(value = "/apply")
	public ModelAndView apply(@RequestParam final int requestId) {
		ModelAndView res = null;
		final Request o = this.requestService.findOne(requestId);
		Assert.notNull(o);
		Assert.isTrue(o.getBanned() == false);
		final Application a = new Application();
		a.setState("PENDING");
		a.setCustomer((Customer) this.loginService.getPrincipalActor());
		a.setDemand(o);
		this.applicationService.save(a);
		res = new ModelAndView("redirect:../../actor/request/list.do");
		return res;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final Request request = this.requestService.create();
		request.setCustomer((Customer) this.loginService.getPrincipalActor());
		result = this.createEditModelAndView(request);
		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save2(@Valid final Request request, final BindingResult binding) {
		ModelAndView result;
		Assert.isTrue(request.getCustomer().getId() == (this.loginService.getPrincipalActor().getId()));
		if (binding.hasErrors())
			result = this.createEditModelAndView(request);
		else
			try {
				this.requestService.save(request);
				result = new ModelAndView("redirect:/actor/request/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(request, "commit.error");
			}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Request request) {
		ModelAndView result;

		result = this.createEditModelAndView(request, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Request request, final String message) {
		ModelAndView result;
		result = new ModelAndView("customer/request/edit");
		result.addObject("request", request);
		result.addObject("message", message);

		return result;
	}
}
