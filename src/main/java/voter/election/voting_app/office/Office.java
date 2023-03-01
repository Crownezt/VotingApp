package voter.election.voting_app.office;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import voter.election.voting_app.candidate.Candidate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String duration;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Candidate candidate;

}
