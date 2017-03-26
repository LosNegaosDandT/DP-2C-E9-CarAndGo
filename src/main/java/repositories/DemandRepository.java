
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Demand;

@Repository
public interface DemandRepository extends JpaRepository<Demand, Integer> {

	@Query("select count(o)*1.0/(select count(r) from Request r) from Offer o")
	public Double query1();

	@Query("select count(o)*1.0/(select count(c) from Customer c) from Offer o")
	public Double query2_1();

	@Query("select count(r)*1.0/(select count(c) from Customer c) from Request r")
	public Double query2_2();

	@Query("select count(a)*1.0/(select count(d) from Demand d) from Application a")
	public Double query3();
}
