package voter.election.voting_app.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import voter.election.voting_app.data.models.Voter;

public interface VoterRepository extends JpaRepository<Voter,Long> {
Voter findByUserDetails_Email(String email);

}
