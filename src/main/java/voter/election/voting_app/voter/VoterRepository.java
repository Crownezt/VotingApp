package voter.election.voting_app.voter;

import org.springframework.data.jpa.repository.JpaRepository;
import voter.election.voting_app.candidate.Candidate;

import java.util.List;

public interface VoterRepository extends JpaRepository<Voter,Long> {

}
