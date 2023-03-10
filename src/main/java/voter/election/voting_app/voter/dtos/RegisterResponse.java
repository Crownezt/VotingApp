package voter.election.voting_app.voter.dtos;

import jakarta.persistence.Id;
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
