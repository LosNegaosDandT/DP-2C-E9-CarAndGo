
package domain;

import java.util.Collection;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public interface Commentable {

	public Collection<Comment> getComments();
	public void setComments(Collection<Comment> comments);
}
