
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.DemandRepository;
import domain.Demand;

@Service
@Transactional
public class DemandService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private DemandRepository	demandRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public DemandService() {
		super();
	}

	// Simple CRUD methods ---------------------------------------------------- 

	public Collection<Demand> findAll() {
		final Collection<Demand> res = this.demandRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Demand findOne(final int demandId) {
		return this.demandRepository.findOne(demandId);
	}

	public Demand save(final Demand demand) {
		Assert.notNull(demand);
		return this.demandRepository.save(demand);
	}

	public void delete(final Demand demand) {
		this.demandRepository.delete(demand);
	}
	public void flush() {
		this.demandRepository.flush();
	}

	// Other business methods -------------------------------------------------

	public Double query1() {
		return this.demandRepository.query1();
	}

	public List<Double> query2() {
		final List<Double> res = new ArrayList<>();
		res.add(this.demandRepository.query2_1());
		res.add(this.demandRepository.query2_2());
		return res;
	}

	public Double query3() {
		return this.demandRepository.query3();
	}
}
