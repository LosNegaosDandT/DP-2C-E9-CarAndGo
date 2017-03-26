
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Message() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private Date	date;
	private String	title;
	private String	text;
	private boolean	sent;


	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getDate() {
		return this.date;
	}
	public void setDate(final Date date) {
		this.date = date;
	}
	@NotBlank
	public String getTitle() {
		return this.title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}
	@NotBlank
	public String getText() {
		return this.text;
	}
	public void setText(final String text) {
		this.text = text;
	}

	@NotNull
	public boolean getSent() {
		return this.sent;
	}
	public void setSent(final boolean sent) {
		this.sent = sent;
	}


	// Relationships -------------------------------------------------------------

	private Collection<Attachment>	attachments;
	private Actor					owner;
	private Actor					sender;
	private Actor					recipient;


	@NotNull
	@ManyToOne(optional = false)
	@Valid
	public Actor getRecipient() {
		return this.recipient;
	}
	public void setRecipient(final Actor recipient) {
		this.recipient = recipient;
	}
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Actor getSender() {
		return this.sender;
	}
	public void setSender(final Actor sender) {
		this.sender = sender;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Actor getOwner() {
		return this.owner;
	}
	public void setOwner(final Actor owner) {
		this.owner = owner;
	}
	@NotNull
	@OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
	public Collection<Attachment> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(final Collection<Attachment> attachments) {
		this.attachments = attachments;
	}

}
