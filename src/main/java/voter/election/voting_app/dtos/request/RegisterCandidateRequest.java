package voter.election.voting_app.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import voter.election.voting_app.Gender;
import voter.election.voting_app.data.models.Office;
import voter.election.voting_app.data.models.Party;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterCandidateRequest {
    private String name;
    private String email;
    private Gender gender;
    private int age;
    private String phoneNumber;
    private Office office;
    private Party party;
    private String password;
}
