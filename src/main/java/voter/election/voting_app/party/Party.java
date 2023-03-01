package voter.election.voting_app.party;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import voter.election.voting_app.app_user.AppUser;
import voter.election.voting_app.candidate.Candidate;
import voter.election.voting_app.office.Office;

import java.util.Map;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Map<Office, Candidate> Candidates;
}
