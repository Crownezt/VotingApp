package voter.election.voting_app.vote.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import voter.election.voting_app.candidate.Candidate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteResponse {
    private String message;
    private Candidate candidateId;


}
