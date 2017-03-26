/*
 * 
 * Copyright (C) FAC
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Offer extends Demand {

	// Constructors -----------------------------------------------------------

	public Offer() {
		super();
	}


	// Attributes -------------------------------------------------------------

	// Relationships ----------------------------------------------------------
	private Customer	customer;


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
