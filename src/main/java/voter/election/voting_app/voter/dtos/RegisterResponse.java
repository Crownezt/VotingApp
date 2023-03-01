package voter.election.voting_app.voter.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {
    private Long id;
    private String message;
    private int code;
    private boolean successful;
}
