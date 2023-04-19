package voter.election.voting_app.services.interfaces;

import com.github.fge.jsonpatch.JsonPatch;
import jakarta.mail.MessagingException;
import voter.election.voting_app.data.models.Candidate;
import voter.election.voting_app.data.models.Party;
import voter.election.voting_app.data.models.Voter;
import voter.election.voting_app.dtos.request.RegisterVoterRequest;
import voter.election.voting_app.dtos.response.RegisterResponse;

import java.util.List;
import java.util.Optional;

public interface VoterServices {
    RegisterResponse register(RegisterVoterRequest request) throws MessagingException;
    Voter getVoterById(long id);
    List<Voter> getVoter();
    Voter updateVoter(long id, JsonPatch updatePayLoad);
    List<Candidate> viewCandidates();
    Optional<Candidate> viewCandidateById(long id);

    List<Party> viewParties();


}
