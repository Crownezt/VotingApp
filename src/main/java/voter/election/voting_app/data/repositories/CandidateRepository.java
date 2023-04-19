package voter.election.voting_app.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import voter.election.voting_app.data.models.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate findByUserDetails_Email(String email);
}
