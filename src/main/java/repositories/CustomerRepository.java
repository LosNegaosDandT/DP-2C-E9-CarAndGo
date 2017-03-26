
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select a.customer,sum(Case When a.state = 'ACCEPTED' Then 1 Else 0 End) from Application a where a.state = 'ACCEPTED' group by a.customer order by sum(Case When a.state = 'ACCEPTED' Then 1 Else 0 End) DESC")
	public List<Object[]> query4();

	@Query("select a.customer,sum(Case When a.state = 'DENIED' Then 1 Else 0 End) from Application a where a.state = 'DENIED' group by a.customer order by sum(Case When a.state = 'DENIED' Then 1 Else 0 End) DESC")
	public List<Object[]> query5();
}
