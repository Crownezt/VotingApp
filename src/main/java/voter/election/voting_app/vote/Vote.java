package voter.election.voting_app.vote;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import voter.election.voting_app.candidate.Candidate;
import voter.election.voting_app.voter.Voter;

public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Voter voterId;
    private Candidate candidateId;


}
