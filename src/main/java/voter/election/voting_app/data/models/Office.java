package voter.election.voting_app.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
