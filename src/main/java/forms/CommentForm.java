
package forms;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class CommentForm {

	// ATTRIBUTES -------------------------
	private int		id;
	private String	title;
	private String	text;
	private int		stars;


	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Range(min = 0, max = 5)
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	@NotBlank
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	// ATTRIBUTES GETTERS AND SETTERS -----

}
