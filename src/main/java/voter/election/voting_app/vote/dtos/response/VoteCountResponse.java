package voter.election.voting_app.vote.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import voter.election.voting_app.candidate.Candidate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteCountResponse {
    private String message;
    private long candidateId;
    private long voteCount;
}
