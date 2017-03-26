
package forms;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class RegisterCustomerForm {

	private String	username;
	private String	password;
	private String	passwordConfirm;

	private String	name;
	private String	surname;
	private String	email;
	private String	phone;


	// Constructor
	public RegisterCustomerForm() {
		super();
	}

	//Getters and Setters 

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

	@NotBlank
	@Size(min = 5, max = 32)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@NotBlank
	@Size(min = 5, max = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@NotBlank
	@Size(min = 5, max = 32)
	public String getPasswordConfirm() {
		return this.passwordConfirm;
	}

	public void setPasswordConfirm(final String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
}
