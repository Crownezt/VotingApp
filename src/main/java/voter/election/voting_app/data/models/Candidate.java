package voter.election.voting_app.data.models;

import jakarta.persistence.*;
import lombok.*;
import voter.election.voting_app.Gender;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    private int age;
}
