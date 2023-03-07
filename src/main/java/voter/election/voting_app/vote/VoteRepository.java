package voter.election.voting_app.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import voter.election.voting_app.candidate.Candidate;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    public long countVotesByCandidateId(Candidate candidateId);
}
