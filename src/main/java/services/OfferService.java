
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.OfferRepository;
import security.LoginService;
import domain.Application;
import domain.Comment;
import domain.Customer;
import domain.Offer;

@Service
@Transactional
public class OfferService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private OfferRepository	offerRepository;

	@Autowired
	private LoginService	loginService;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public OfferService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Offer create() {
		Assert.isTrue(LoginService.isPrincipalCustomer());
		final Offer r = new Offer();
		r.setCustomer((Customer) this.loginService.getPrincipalActor());
		r.setApplications(new ArrayList<Application>());
		r.setComments(new ArrayList<Comment>());
		r.setBanned(false);
		return r;
	}

	public Collection<Offer> findAll() {
		final Collection<Offer> res = this.offerRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Offer findOne(final int offerId) {
		return this.offerRepository.findOne(offerId);
	}

	public Offer save(final Offer offer) {
		Assert.notNull(offer);
		//Assert.isTrue(this.loginService.getPrincipalActor().getId() == offer.getCustomer().getId());
		return this.offerRepository.save(offer);
	}

	public void delete(final Offer offer) {
		this.offerRepository.delete(offer);
	}

	public void flush() {
		this.offerRepository.flush();
	}
	// Other business methods -------------------------------------------------

	public Collection<Offer> searchRequests(final String text) {
		Collection<Offer> res = null;
		if (LoginService.isPrincipalAdmin()) {
			if (text == null)
				res = this.offerRepository.searchOffersWithBanneds("%%");
			else
				res = this.offerRepository.searchOffersWithBanneds("%" + text + "%");
		} else if (LoginService.isPrincipalCustomer())
			if (text == null)
				res = this.offerRepository.searchOffersWithMyBanneds("%%", this.loginService.getPrincipalActor().getId());
			else
				res = this.offerRepository.searchOffersWithMyBanneds("%" + text + "%", this.loginService.getPrincipalActor().getId());
		return res;
	}
}
