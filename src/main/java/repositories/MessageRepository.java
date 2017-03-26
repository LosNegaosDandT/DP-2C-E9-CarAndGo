
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Query("select m from Message m where m.sender = ?1 and m.owner = ?1")
	Collection<Message> getSentMessages(Actor a);

	@Query("select m from Message m where m.recipient = ?1 and m.owner = ?1")
	Collection<Message> getReceivedMessages(Actor a);

}
