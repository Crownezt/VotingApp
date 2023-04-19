package voter.election.voting_app.dtos.response;

import lombok.*;
import voter.election.voting_app.data.models.Candidate;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class VoteResponse {
    @NonNull
    private String message;
    private Long candidateId;

}
