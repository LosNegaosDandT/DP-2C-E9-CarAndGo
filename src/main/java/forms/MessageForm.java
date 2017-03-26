
package forms;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import domain.Actor;

public class MessageForm {

	// Constructors
	public MessageForm() {
		super();
	}


	//Attributes

	private Actor	recipient;
	private String	title;
	private String	text;


	@NotNull
	public Actor getRecipient() {
		return this.recipient;
	}

	public void setRecipient(final Actor recipient) {
		this.recipient = recipient;
	}

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getText() {
		return this.text;
	}

	@NotBlank
	public void setText(final String text) {
		this.text = text;
	}

}
