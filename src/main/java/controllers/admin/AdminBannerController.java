
package controllers.admin;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BannerService;
import controllers.AbstractController;
import domain.Banner;

@Controller
@RequestMapping("/admin/banner/")
public class AdminBannerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private BannerService	bannerService;


	// Constructors -----------------------------------------------------------
	public AdminBannerController() {
		super();
	}

	// Edition ----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Collection<Banner> r;
		r = this.bannerService.findAll();
		Assert.isTrue(r.size() == 1);
		final Banner b = ((List<Banner>) r).get(0);
		Assert.notNull(b);
		result = this.createEditModelAndView(b);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Banner banner, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(banner);
		else
			try {
				this.bannerService.save(banner);
				result = new ModelAndView("redirect:../../welcome/index.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(banner, "commit.error");
			}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Banner r, final String s) {
		return this.createEditModelAndView(r, s);
	}

	protected ModelAndView createEditModelAndView(final Banner r) {
		ModelAndView result;
		result = new ModelAndView("banner/edit");
		result.addObject("banner", r);
		return result;
	}

}
