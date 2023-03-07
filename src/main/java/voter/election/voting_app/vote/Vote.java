package voter.election.voting_app.vote;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import voter.election.voting_app.candidate.Candidate;
import voter.election.voting_app.voter.Voter;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Voter voterId;
    @OneToOne
    private Candidate candidateId;
//    private int voteCounter = 0;



}
