/*
 * CustomerController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import domain.Customer;
import forms.RegisterCustomerForm;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	//Services

	@Autowired
	private CustomerService	customerService;


	// Constructors -----------------------------------------------------------

	public CustomerController() {
		super();
	}

	//Register ----------------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		final ModelAndView res = new ModelAndView("customer/register");
		final RegisterCustomerForm registerCustomerForm = new RegisterCustomerForm();
		res.addObject("requestURI", "customer/register.do");
		res.addObject("registerCustomerForm", registerCustomerForm);
		return res;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView register(@Valid final RegisterCustomerForm registerCustomerForm, final BindingResult bindingResult) {

		ModelAndView res = new ModelAndView("customer/register");
		Customer customer = this.customerService.create();
		customer = this.customerService.reconstruct(registerCustomerForm, customer);

		if (registerCustomerForm.getPassword().equals(registerCustomerForm.getPasswordConfirm())) {

			if (bindingResult.hasErrors()) {
				System.out.println(bindingResult.getAllErrors());
				res.addObject("requestURI", "customer/register.do");
				res.addObject("registerCustomerForm", registerCustomerForm);
			} else
				try {
					this.customerService.save(customer);
					res = new ModelAndView("redirect:../welcome/index.do");

				} catch (final DataIntegrityViolationException oops) {
					System.out.println(oops.getMessage());
					res = new ModelAndView("customer/register");
					res.addObject("customer", customer);
					res.addObject("message", "customer.error.exists");

				} catch (final Throwable oops) {

					System.out.println(oops.getMessage());
					res.addObject("message", "customer.commit.error");

				}
		} else {
			res.addObject("registerCustomerForm", registerCustomerForm);
			res.addObject("message", "customer.password.error");
		}

		return res;
	}

}
