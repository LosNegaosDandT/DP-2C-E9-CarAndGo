
package controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RequestService;
import controllers.AbstractController;
import domain.Request;

@Controller
@RequestMapping("admin/request/")
public class AdminRequestController extends AbstractController {

	//Services

	@Autowired
	private RequestService	requestService;


	@RequestMapping(value = "/ban")
	public ModelAndView ban(@RequestParam final int requestId) {
		ModelAndView res = null;
		final Request o = this.requestService.findOne(requestId);
		Assert.notNull(o);
		Assert.isTrue(o.getBanned() == false);
		o.setBanned(true);
		this.requestService.save(o);
		res = new ModelAndView("redirect:../../actor/request/list.do");
		return res;
	}
}
