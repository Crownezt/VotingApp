package voter.election.voting_app.party;

import com.github.fge.jsonpatch.JsonPatch;
import jakarta.servlet.http.Part;
import voter.election.voting_app.candidate.Candidate;
import voter.election.voting_app.candidate.dtos.RegisterResponse;

import java.util.List;

public interface PartyServices {
    RegisterResponse register(String name);
    Party getCandidateById(long id);
    List<Party> viewParties();
}
