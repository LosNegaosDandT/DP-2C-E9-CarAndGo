
package controllers.actor;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ActorService;
import services.MessageService;
import controllers.AbstractController;
import domain.Actor;
import domain.Message;
import forms.MessageForm;

@Controller
@RequestMapping("/actor/message")
public class ActorMessageController extends AbstractController {

	@Autowired
	private LoginService	loginService;
	@Autowired
	private MessageService	messageService;
	@Autowired
	private ActorService	actorService;


	@RequestMapping("/sents")
	public ModelAndView sent() {
		Actor actor = null;
		final ModelAndView res = new ModelAndView("message/list");
		actor = this.loginService.getPrincipalActor();
		res.addObject("messages", actor.getSentMessages());
		return res;
	}

	@RequestMapping("/receiveds")
	public ModelAndView received() {
		Actor actor = null;
		final ModelAndView res = new ModelAndView("message/list");
		actor = this.loginService.getPrincipalActor();
		res.addObject("messages", actor.getReceivedMessages());
		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView res = new ModelAndView("message/edit");
		final MessageForm m = new MessageForm();
		m.setText("");
		m.setTitle("");
		res.addObject("actors", this.actorService.findAll());
		res.addObject("messageForm", m);
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final MessageForm messageForm, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = new ModelAndView("message/edit");
		else
			try {
				final Message message = this.messageService.reconstruct(messageForm, this.messageService.create());
				message.setDate(new Date());
				//el save hace la copia
				this.messageService.save(message);
				result = new ModelAndView("redirect:sents.do");
			} catch (final Throwable oops) {
				result = new ModelAndView("message/edit");
				result.addObject("message", "commit.error");
			}
		return result;
	}
}
