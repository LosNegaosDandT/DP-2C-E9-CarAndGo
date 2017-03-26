
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RequestRepository;
import security.LoginService;
import domain.Application;
import domain.Comment;
import domain.Customer;
import domain.Request;

@Service
@Transactional
public class RequestService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private RequestRepository	requestRepository;

	@Autowired
	private LoginService		loginService;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public RequestService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Request create() {
		Assert.isTrue(LoginService.isPrincipalCustomer());
		final Request r = new Request();
		r.setCustomer((Customer) this.loginService.getPrincipalActor());
		r.setApplications(new ArrayList<Application>());
		r.setComments(new ArrayList<Comment>());
		r.setBanned(false);
		return r;
	}
	public Collection<Request> findAll() {
		final Collection<Request> res = this.requestRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Request findOne(final int requestId) {
		return this.requestRepository.findOne(requestId);
	}

	public Request save(final Request request) {
		Assert.notNull(request);
		Assert.isTrue(this.loginService.getPrincipalActor().getId() == request.getCustomer().getId());
		return this.requestRepository.save(request);
	}

	public void delete(final Request request) {
		this.requestRepository.delete(request);
	}
	public void flush() {
		this.requestRepository.flush();
	}

	// Other business methods -------------------------------------------------

	public Collection<Request> searchRequests(final String text) {
		Collection<Request> res = null;
		if (LoginService.isPrincipalAdmin()) {
			if (text == null)
				res = this.requestRepository.searchOffersWithBanneds("%%");
			else
				res = this.requestRepository.searchOffersWithBanneds("%" + text + "%");
		} else if (LoginService.isPrincipalCustomer())
			if (text == null)
				res = this.requestRepository.searchOffersWithMyBanneds("%%", this.loginService.getPrincipalActor().getId());
			else
				res = this.requestRepository.searchOffersWithMyBanneds("%" + text + "%", this.loginService.getPrincipalActor().getId());
		return res;
	}
}
