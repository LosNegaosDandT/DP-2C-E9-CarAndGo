/*
 * 
 * Copyright (C) FAC
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Demand extends DomainEntity implements Commentable {

	// Constructors -----------------------------------------------------------

	public Demand() {
		super();
	}


	// Attributes -------------------------------------------------------------
	private String	title;
	private String	description;
	private Date	moment;
	private Place	origin;
	private Place	destination;
	private boolean	banned;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@NotNull
	@Valid
	@AttributeOverrides({
		@AttributeOverride(name = "address", column = @Column(name = "originAddress")), @AttributeOverride(name = "latitude", column = @Column(name = "originLatitude")), @AttributeOverride(name = "longitude", column = @Column(name = "originLongitude"))
	})
	public Place getOrigin() {
		return this.origin;
	}

	public void setOrigin(final Place origin) {
		this.origin = origin;
	}

	@NotNull
	@Valid
	@AttributeOverrides({
		@AttributeOverride(name = "address", column = @Column(name = "destinationAddress")), @AttributeOverride(name = "latitude", column = @Column(name = "destinationLatitude")),
		@AttributeOverride(name = "longitude", column = @Column(name = "destinationLongitude"))
	})
	public Place getDestination() {
		return this.destination;
	}

	public void setDestination(final Place destination) {
		this.destination = destination;
	}

	public boolean getBanned() {
		return this.banned;
	}

	public void setBanned(final boolean banned) {
		this.banned = banned;
	}


	// Relationships ----------------------------------------------------------
	private Collection<Application>	applications;


	@NotNull
	@OneToMany(mappedBy = "demand")
	public Collection<Application> getApplications() {
		return this.applications;
	}
	public void setApplications(final Collection<Application> applications) {
		this.applications = applications;
	}


	// Implement methods  ---------------------------------------------------------
	private Collection<Comment>	comments;


	@Override
	@OneToMany(cascade = CascadeType.ALL)
	@NotNull
	public Collection<Comment> getComments() {
		return this.comments;
	}

	@Override
	public void setComments(final Collection<Comment> comments) {
		this.comments = comments;
	}
}
