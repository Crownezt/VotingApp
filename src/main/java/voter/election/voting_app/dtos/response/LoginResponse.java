package voter.election.voting_app.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    public String message;
    public boolean loginSuccess;
}
