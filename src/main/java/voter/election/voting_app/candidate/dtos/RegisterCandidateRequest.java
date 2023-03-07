package voter.election.voting_app.candidate.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import voter.election.voting_app.Gender;
import voter.election.voting_app.candidate.Candidate;
import voter.election.voting_app.office.Office;
import voter.election.voting_app.party.Party;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterCandidateRequest {
    private String firstName;
    private String lastName;
    private String otherName;
    private String email;
    private Gender gender;
    private String phoneNumber;
    private Office office;
    private Party party;

}
