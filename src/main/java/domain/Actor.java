
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends DomainEntity implements Commentable {

	// Constructors -----------------------------------------------------------

	public Actor() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private String	name;
	private String	surname;
	private String	email;
	private String	phone;


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@Email
	@NotBlank
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@NotBlank
	@Pattern(regexp = "^\\+\\d{1,3}[ ]?\\d{6,14}$")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}


	// Relationships ----------------------------------------------------------

	private UserAccount			userAccount;
	private Collection<Message>	messages;
	private Collection<Message>	sentMessages;
	private Collection<Message>	receivedMessages;


	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@NotNull
	@OneToMany(mappedBy = "sender")
	public Collection<Message> getSentMessages() {
		return this.sentMessages;
	}

	public void setSentMessages(final Collection<Message> m) {
		this.sentMessages = m;
	}

	@NotNull
	@OneToMany(mappedBy = "owner")
	public Collection<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(final Collection<Message> m) {
		this.messages = m;
	}

	@NotNull
	@OneToMany(mappedBy = "recipient")
	public Collection<Message> getReceivedMessages() {
		return this.receivedMessages;
	}

	public void setReceivedMessages(final Collection<Message> m) {
		this.receivedMessages = m;
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
