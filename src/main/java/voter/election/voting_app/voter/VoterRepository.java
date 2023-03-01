package voter.election.voting_app.voter;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter,Long> {
}
