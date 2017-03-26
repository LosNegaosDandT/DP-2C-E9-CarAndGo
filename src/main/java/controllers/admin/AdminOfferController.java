
package controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.OfferService;
import controllers.AbstractController;
import domain.Offer;

@Controller
@RequestMapping("admin/offer/")
public class AdminOfferController extends AbstractController {

	//Services

	@Autowired
	private OfferService	offerService;


	@RequestMapping(value = "/ban")
	public ModelAndView ban(@RequestParam final int offerId) {
		ModelAndView res = null;
		final Offer o = this.offerService.findOne(offerId);
		Assert.notNull(o);
		Assert.isTrue(o.getBanned() == false);
		o.setBanned(true);
		this.offerService.save(o);
		res = new ModelAndView("redirect:../../actor/offer/list.do");
		return res;
	}
}
