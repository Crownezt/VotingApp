package voter.election.voting_app.voter;

import com.github.fge.jsonpatch.JsonPatch;
import voter.election.voting_app.candidate.Candidate;
import voter.election.voting_app.party.Party;
import voter.election.voting_app.voter.dtos.RegisterResponse;
import voter.election.voting_app.voter.dtos.RegisterVoterRequest;

import java.util.List;

public interface VoterServices {
    RegisterResponse register(RegisterVoterRequest request);
    Voter updateProfile(long id, JsonPatch updatePayLoad);
    Voter checkStatus(long id);
    List<Candidate> viewCandidates();
    List<Party> viewParties();


}
