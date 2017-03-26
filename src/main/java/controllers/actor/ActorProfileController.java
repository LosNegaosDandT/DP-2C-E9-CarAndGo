/*
 * ProfileController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.CustomerService;
import controllers.AbstractController;
import domain.Administrator;
import domain.Customer;

@Controller
@RequestMapping("/actor/profile")
public class ActorProfileController extends AbstractController {

	@Autowired
	private AdministratorService	adminService;

	@Autowired
	private CustomerService			customerService;


	// Customer ---------------------------------------------------------------		

	@RequestMapping("/customer")
	public ModelAndView profileCustomer(@RequestParam final int actorId) {
		ModelAndView result;
		final Customer actor = this.customerService.findOne(actorId);
		result = new ModelAndView("customer/profile");
		result.addObject("actor", actor);
		return result;
	}

	// Admin ---------------------------------------------------------------		

	@RequestMapping("/admin")
	public ModelAndView profileAdmin(@RequestParam final int actorId) {
		ModelAndView result;
		final Administrator actor = this.adminService.findOne(actorId);
		result = new ModelAndView("admin/profile");
		result.addObject("actor", actor);
		return result;
	}

}
