package voter.election.voting_app.services.interfaces;

import voter.election.voting_app.dtos.request.InviteAdminRequest;
import voter.election.voting_app.dtos.response.PartyResponse;
import voter.election.voting_app.dtos.response.RegisterResponse;
import voter.election.voting_app.data.models.Admin;
import voter.election.voting_app.data.models.Party;

import java.util.List;

public interface AdminServices {
    RegisterResponse register(InviteAdminRequest request);
    Admin getAdminById(long id);
    List<Party> viewParties();
    PartyResponse deleteCandidateById(long id);
    PartyResponse deleteVoterById(long id);
}
