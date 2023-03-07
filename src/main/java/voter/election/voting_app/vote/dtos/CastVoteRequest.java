package voter.election.voting_app.vote.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import voter.election.voting_app.candidate.Candidate;
import voter.election.voting_app.voter.Voter;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class CastVoteRequest {
    private Voter voterId;
    private Candidate candidateId;
}
