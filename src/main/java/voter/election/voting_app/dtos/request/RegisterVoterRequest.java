package voter.election.voting_app.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;
import voter.election.voting_app.Gender;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterVoterRequest {
    private String name;
    private String email;
    private Gender gender;
    private int age;
    private String password;
    private MultipartFile image;
}