
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Application extends DomainEntity {

	// Constructors -----------------------------------------------------------
	public Application() {
		super();
	}


	// Attributes -----------------------------------------------------------
	private String	state;


	@NotBlank
	@Pattern(regexp = "^((PENDING)|(ACCEPTED)|(DENIED))$", message = "Only can be 'PENDING', 'ACCEPTED' or 'DENIED'.")
	public String getState() {
		return this.state;
	}
	public void setState(final String state) {
		this.state = state;
	}


	// Relationships -----------------------------------------------------------
	private Demand		demand;
	private Customer	customer;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Demand getDemand() {
		return this.demand;
	}
	public void setDemand(final Demand demand) {
		this.demand = demand;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Customer getCustomer() {
		return this.customer;
	}
	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

}
