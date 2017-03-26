
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AttachmentRepository;
import domain.Attachment;

@Service
@Transactional
public class AttachmentService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private AttachmentRepository	attachmentRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public AttachmentService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Attachment create() {
		final Attachment r = new Attachment();
		return r;
	}

	public Collection<Attachment> findAll() {
		final Collection<Attachment> res = this.attachmentRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Attachment findOne(final int attachmentId) {
		return this.attachmentRepository.findOne(attachmentId);
	}

	public Attachment save(final Attachment attachment) {
		Assert.notNull(attachment);
		return this.attachmentRepository.save(attachment);
	}

	public void delete(final Attachment attachment) {
		this.attachmentRepository.delete(attachment);
	}

	public void flush() {
		this.attachmentRepository.flush();
	}
	// Other business methods -------------------------------------------------

}
