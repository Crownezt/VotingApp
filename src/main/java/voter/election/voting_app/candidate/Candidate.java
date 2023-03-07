package voter.election.voting_app.candidate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import voter.election.voting_app.Gender;
import voter.election.voting_app.app_user.AppUser;
import voter.election.voting_app.office.Office;
import voter.election.voting_app.party.Party;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private AppUser userDetails;
    private String phoneNumber;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Office office;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Party party;


}
