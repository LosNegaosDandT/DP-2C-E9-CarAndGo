
package controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.DemandService;
import controllers.AbstractController;

@Controller
@RequestMapping("admin/dashboard/")
public class AdminDashboardController extends AbstractController {

	//Services
	@Autowired
	private CustomerService	customerService;

	@Autowired
	private DemandService	demandService;


	@RequestMapping(value = "/display")
	public ModelAndView list() {
		final ModelAndView res = new ModelAndView("dashboard");
		res.addObject("query1", this.demandService.query1());
		res.addObject("query2_1", this.demandService.query2().get(0));
		res.addObject("query2_2", this.demandService.query2().get(1));
		res.addObject("query3", this.demandService.query3());
		res.addObject("query4_1", this.customerService.query4()[0]);
		res.addObject("query4_2", this.customerService.query4()[1]);
		res.addObject("query5_1", this.customerService.query5()[0]);
		res.addObject("query5_2", this.customerService.query5()[1]);
		return res;

	}
}
