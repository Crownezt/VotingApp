package voter.election.voting_app.admin.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterAdminRequest {
    private String firstName;
    private String lastName;
    private String otherName;
    private String email;
    private String employeeId;

}
