package voter.election.voting_app.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import voter.election.voting_app.data.models.Candidate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartyResponse {
    private String message;
    private long id;
}
