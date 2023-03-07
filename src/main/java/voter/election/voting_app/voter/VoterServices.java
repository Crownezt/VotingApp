package voter.election.voting_app.voter;

import com.github.fge.jsonpatch.JsonPatch;
import voter.election.voting_app.candidate.Candidate;
import voter.election.voting_app.party.Party;
import voter.election.voting_app.voter.dtos.RegisterResponse;
import voter.election.voting_app.voter.dtos.RegisterVoterRequest;

import java.util.List;
import java.util.Optional;

public interface VoterServices {
    RegisterResponse register(RegisterVoterRequest request);
    Voter getVoterById(long id);
    List<Voter> getVoter();
    Voter updateVoter(long id, JsonPatch updatePayLoad);
    List<Candidate> viewCandidates();
    Optional<Candidate> viewCandidateById(long id);

    List<Party> viewParties();


}
