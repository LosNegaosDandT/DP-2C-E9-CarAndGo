
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BannerRepository;
import domain.Banner;

@Service
@Transactional
public class BannerService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private BannerRepository	bannerRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public BannerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Banner create() {
		final Banner r = new Banner();
		return r;
	}

	public Collection<Banner> findAll() {
		final Collection<Banner> res = this.bannerRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Banner findOne(final int bannerId) {
		return this.bannerRepository.findOne(bannerId);
	}

	public Banner save(final Banner banner) {
		Assert.notNull(banner);
		return this.bannerRepository.save(banner);
	}

	public void delete(final Banner banner) {
		this.bannerRepository.delete(banner);
	}

	public void flush() {
		this.bannerRepository.flush();
	}

	// Other business methods -------------------------------------------------

}
