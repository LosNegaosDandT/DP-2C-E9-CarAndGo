
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

	//Buscador
	//	@Query("select r from Offer r where r.banned = false and (r.title like %?1% or r.description like %?1% or r.origin.address like %?1% or r.destination.address like %?1%)")
	//	public Collection<Offer> searchRequestsMyBaneadas(String text);

	//Buscador con baneadas (admin)
	@Query("select r from Offer r where (r.title like ?1 or r.description like ?1 or r.origin.address like ?1 or r.destination.address like ?1)")
	public Collection<Offer> searchOffersWithBanneds(String text);

	//Buscador con mis baneadas
	@Query("select r from Offer r where (r.title like ?1 or r.description like ?1 or r.origin.address like ?1 or r.destination.address like ?1) and (r.customer.id = ?2 or r.banned = false)")
	public Collection<Offer> searchOffersWithMyBanneds(String text, int id);

	//Offers no baneadas
	//	@Query("select r from Offer r where r.banned = false")
	//	public Collection<Offer> findAllNoBanned();

	//Offers baneadas
	//	@Query("select r from Offer r where r.banned = true")
	//	public Collection<Offer> findAllBanned();
	//	ban mio		SI		SI
	//	ban otro	NO		NO
	//	noban mio	SI		SI
	//	noban otro	SI		SI
}
