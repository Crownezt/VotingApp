package voter.election.voting_app.admin.dtos;

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
    private int code;
    private boolean successful;
}
