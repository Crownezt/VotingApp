package voter.election.voting_app.voter.dtos;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import voter.election.voting_app.Gender;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterVoterRequest {
    private String firstName;
    private String lastName;
    private String otherName;
    private String email;
    private Gender gender;
    private int age;
}