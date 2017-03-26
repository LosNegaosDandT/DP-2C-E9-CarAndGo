
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Comment;
import forms.CommentForm;

@Service
@Transactional
public class CommentService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private CommentRepository	commentRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public CommentService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Comment create() {
		final Comment comment = new Comment();
		comment.setTitle("");
		comment.setText("");
		comment.setStars(0);
		comment.setMoment(new Date());
		comment.setBanned(false);
		return comment;
	}

	public Collection<Comment> findAll() {
		final Collection<Comment> res = this.commentRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Comment findOne(final int commentId) {
		return this.commentRepository.findOne(commentId);
	}

	public Comment save(final Comment comment) {
		Assert.notNull(comment);
		return this.commentRepository.save(comment);
	}

	public void delete(final Comment comment) {
		this.commentRepository.delete(comment);
	}

	public void flush() {
		this.commentRepository.flush();
	}

	// Other business methods -------------------------------------------------

	public Comment reconstruct(final CommentForm commentForm, final Comment comment) {
		comment.setBanned(false);
		comment.setMoment(new Date());
		comment.setStars(commentForm.getStars());
		comment.setText(commentForm.getText());
		comment.setTitle(commentForm.getTitle());

		return comment;
	}
}
