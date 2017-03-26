
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import domain.Application;

@Service
@Transactional
public class ApplicationService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ApplicationRepository	applicationRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public ApplicationService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Application create() {
		final Application r = new Application();
		return r;
	}

	public Collection<Application> findAll() {
		final Collection<Application> res = this.applicationRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Application findOne(final int applicationId) {
		return this.applicationRepository.findOne(applicationId);
	}

	public Application save(final Application application) {
		Assert.notNull(application);
		return this.applicationRepository.save(application);
	}

	public void delete(final Application application) {
		this.applicationRepository.delete(application);
	}
	public void flush() {
		this.applicationRepository.flush();
	}

	// Other business methods -------------------------------------------------

}
