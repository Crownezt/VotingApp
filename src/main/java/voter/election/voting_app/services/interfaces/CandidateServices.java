package voter.election.voting_app.services.interfaces;

import com.github.fge.jsonpatch.JsonPatch;
import voter.election.voting_app.dtos.request.RegisterCandidateRequest;
import voter.election.voting_app.data.models.Candidate;
import voter.election.voting_app.data.models.Party;
import voter.election.voting_app.dtos.response.RegisterResponse;

import java.util.List;

public interface CandidateServices {
    RegisterResponse register(RegisterCandidateRequest request);
    Candidate getCandidateById(long id);

    Candidate updateCandidate(long id, JsonPatch updatePayLoad);

    List<Party> viewParties();



}

