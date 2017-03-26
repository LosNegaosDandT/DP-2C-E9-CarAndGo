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

import services.OfferService;
import services.RequestService;
import controllers.AbstractController;
import domain.Offer;
import domain.Request;

@Controller
@RequestMapping("/actor/demand/details")
public class ActorDemandDetailsController extends AbstractController {

	@Autowired
	private RequestService	requestService;

	@Autowired
	private OfferService	offerService;


	// request ---------------------------------------------------------------		

	@RequestMapping("/request")
	public ModelAndView detailsRequest(@RequestParam final int demandId) {
		ModelAndView result;
		final Request demand = this.requestService.findOne(demandId);
		result = new ModelAndView("actor/request/details");
		result.addObject("demand", demand);
		return result;
	}

	// offer ---------------------------------------------------------------		

	@RequestMapping("/offer")
	public ModelAndView detailsOffer(@RequestParam final int demandId) {
		ModelAndView result;
		final Offer demand = this.offerService.findOne(demandId);
		result = new ModelAndView("actor/offer/details");
		result.addObject("demand", demand);
		return result;
	}

}
