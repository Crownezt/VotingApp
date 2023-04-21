package voter.election.voting_app.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import voter.election.voting_app.Gender;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InviteAdminRequest {
    private String name;
    private String email;
    private String employeeId;
    private String password;
    private String Password;
    private Gender gender;
    private MultipartFile image;

}
