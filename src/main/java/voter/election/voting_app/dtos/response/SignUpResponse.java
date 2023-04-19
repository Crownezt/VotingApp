package voter.election.voting_app.dtos.response;

import lombok.Data;

@Data
public class SignUpResponse {
    String name;
    String email;
    String token;
}
