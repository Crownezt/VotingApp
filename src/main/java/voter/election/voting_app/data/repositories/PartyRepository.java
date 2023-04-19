package voter.election.voting_app.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import voter.election.voting_app.data.models.Party;

public interface PartyRepository extends JpaRepository<Party, Long> {
}
