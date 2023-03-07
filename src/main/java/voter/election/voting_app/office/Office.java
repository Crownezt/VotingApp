package voter.election.voting_app.office;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import voter.election.voting_app.candidate.Candidate;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Office name;
    private int duration;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Candidate> candidates;

}
