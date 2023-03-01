package voter.election.voting_app.election;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String electionDate;

}
