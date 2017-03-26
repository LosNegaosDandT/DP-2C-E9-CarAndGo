
package controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ApplicationService;
import controllers.AbstractController;
import domain.Application;
import domain.Customer;

@Controller
@RequestMapping("customer/application")
public class CustomerApplicationController extends AbstractController {

	//Services
	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private LoginService		loginService;


	//	@Autowired
	//	private DemandService		demandService;

	//List
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res = new ModelAndView("customer/application/list");
		res.addObject("application", ((Customer) this.loginService.getPrincipalActor()).getApplications());
		//		res.addObject("requestURI", "customer/application/list.do");

		return res;

	}

	@RequestMapping(value = "/deny", method = RequestMethod.GET)
	public ModelAndView deny(@RequestParam final int applicationId) {
		ModelAndView result = null;
		final Application a;
		a = this.applicationService.findOne(applicationId);
		Assert.notNull(a);
		Assert.isTrue(a.getState().equals("PENDING"));
		Assert.isTrue(((Customer) this.loginService.getPrincipalActor()).getApplications().contains(a));
		a.setState("DENIED");
		this.applicationService.save(a);
		result = new ModelAndView("redirect:list.do");
		return result;
	}

	@RequestMapping(value = "/accept", method = RequestMethod.GET)
	public ModelAndView accept(@RequestParam final int applicationId) {
		ModelAndView result = null;
		final Application a;
		a = this.applicationService.findOne(applicationId);
		Assert.notNull(a);
		Assert.isTrue(a.getState().equals("PENDING"));
		Assert.isTrue(((Customer) this.loginService.getPrincipalActor()).getApplications().contains(a));
		a.setState("ACCEPTED");
		this.applicationService.save(a);
		result = new ModelAndView("redirect:list.do");
		return result;
	}
	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(final Application application) {
		ModelAndView result;
		result = this.createEditModelAndView(application, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Application application, final String message) {
		ModelAndView result;
		result = new ModelAndView("customer/application/list");
		result.addObject("finder", application);
		result.addObject("message", message);
		return result;
	}

}
