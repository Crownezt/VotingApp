package voter.election.voting_app.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegisterResponse {
    private Long id;
    private String message;
    private boolean successful;
    private String token;

}
