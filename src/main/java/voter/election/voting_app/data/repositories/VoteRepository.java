package voter.election.voting_app.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import voter.election.voting_app.data.models.Candidate;
import voter.election.voting_app.data.models.Vote;
import voter.election.voting_app.data.models.Voter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findAllByCandidate_Id(long candidateId);

    List<Vote> findAllByCreatedAt(String createdAt);
    Optional<Vote> findVoteByCandidate_IdAndVoter_Id(long candidateId, long voterId);
}