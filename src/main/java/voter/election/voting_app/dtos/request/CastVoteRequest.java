package voter.election.voting_app.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class CastVoteRequest {
    private Long voterId;
    private Long candidateId;
}
