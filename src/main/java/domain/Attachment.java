
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Attachment extends DomainEntity {

	// Constructors
	public Attachment() {
		super();
	}


	// Attributes -----------------------------------------------------------
	private String	url;


	@NotBlank
	@URL
	public String getUrl() {
		return this.url;
	}
	public void setUrl(final String url) {
		this.url = url;
	}


	// Relationships -----------------------------------------------------------
	private Message	message;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Message getMessage() {
		return this.message;
	}
	public void setMessage(final Message message) {
		this.message = message;
	}

}
