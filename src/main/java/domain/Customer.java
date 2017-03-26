
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Actor {

	// Constructors -----------------------------------------------------------

	public Customer() {
		super();
	}


	// Relationships ----------------------------------------------------------

	private Collection<Request>		requests;
	private Collection<Offer>		offers;
	private Collection<Application>	applications;


	@NotNull
	@OneToMany(mappedBy = "customer")
	public Collection<Application> getApplications() {
		return this.applications;
	}
	public void setApplications(final Collection<Application> applications) {
		this.applications = applications;
	}

	@NotNull
	@OneToMany(mappedBy = "customer")
	public Collection<Request> getRequests() {
		return this.requests;
	}

	public void setRequests(final Collection<Request> r) {
		this.requests = r;
	}

	@NotNull
	@OneToMany(mappedBy = "customer")
	public Collection<Offer> getOffers() {
		return this.offers;
	}

	public void setOffers(final Collection<Offer> o) {
		this.offers = o;
	}

}
