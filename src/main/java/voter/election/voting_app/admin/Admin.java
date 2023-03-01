package voter.election.voting_app.admin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import voter.election.voting_app.app_user.AppUser;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Admin {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @OneToOne(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    private AppUser userDetails;
    private String employeeId;
}
