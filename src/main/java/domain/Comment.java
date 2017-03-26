
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity {

	// Constructors —---------------------------------------------------------

	public Comment() {
		super();
	}


	// Attributes —---------------------------------------------------------—

	private String	title;
	private String	text;
	private int		stars;
	private Date	moment;
	private boolean	banned;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String t) {
		this.title = t;
	}

	@NotBlank
	public String getText() {
		return this.text;
	}

	public void setText(final String t) {
		this.text = t;
	}

	@NotNull
	@Range(min = 0, max = 5)
	public int getStars() {
		return this.stars;
	}

	public void setStars(final int s) {
		this.stars = s;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Past
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date m) {
		this.moment = m;
	}

	public boolean getBanned() {
		return this.banned;
	}

	public void setBanned(final boolean banned) {
		this.banned = banned;
	}
	// Relationships —------------------------------------------------------—

}
