package voter.election.voting_app.app_user;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String otherName;
    private String email;
    private String createdAt;
    @Transient
    private MultipartFile profileImage;
}

