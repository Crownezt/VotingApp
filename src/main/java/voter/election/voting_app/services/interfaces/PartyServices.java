package voter.election.voting_app.services.interfaces;

import voter.election.voting_app.data.models.Party;
import voter.election.voting_app.dtos.response.RegisterResponse;

import java.util.List;

public interface PartyServices {
    RegisterResponse register(String name);
    Party getCandidateById(long id);
    List<Party> viewParties();
}
