package voter.election.voting_app.admin;

import voter.election.voting_app.admin.dtos.RegisterAdminRequest;
import voter.election.voting_app.admin.dtos.RegisterResponse;
import voter.election.voting_app.candidate.Candidate;
import voter.election.voting_app.party.Party;
import voter.election.voting_app.voter.Voter;

import java.util.List;

public interface AdminServices {
    RegisterResponse register(RegisterAdminRequest request);
    Admin getAdminById(long id);
    List<Party> viewParties();
    Candidate deleteCandidateById(long id);
    Voter deleteVoterById(long id);
}
