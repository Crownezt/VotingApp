package voter.election.voting_app.candidate;

import com.github.fge.jsonpatch.JsonPatch;
import voter.election.voting_app.candidate.dtos.RegisterCandidateRequest;
import voter.election.voting_app.candidate.dtos.RegisterResponse;
import voter.election.voting_app.party.Party;

import java.util.List;

public interface CandidateServices {
    RegisterResponse register(RegisterCandidateRequest request);
    Candidate getCandidateById(long id);

    Candidate updateCandidate(long id, JsonPatch updatePayLoad);

    List<Party> viewParties();



}

