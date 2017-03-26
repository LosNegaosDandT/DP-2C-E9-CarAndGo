
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

	//Buscador con baneadas (admin)
	@Query("select r from Request r where (r.title like ?1 or r.description like ?1 or r.origin.address like ?1 or r.destination.address like ?1)")
	public Collection<Request> searchOffersWithBanneds(String text);

	//Buscador con mis baneadas
	@Query("select r from Request r where (r.title like ?1 or r.description like ?1 or r.origin.address like ?1 or r.destination.address like ?1) and (r.customer.id = ?2 or r.banned = false)")
	public Collection<Request> searchOffersWithMyBanneds(String text, int id);
}
