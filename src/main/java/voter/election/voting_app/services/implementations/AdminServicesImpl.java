package voter.election.voting_app.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import voter.election.voting_app.data.repositories.PartyRepository;
import voter.election.voting_app.dtos.request.RegisterAdminRequest;
import voter.election.voting_app.dtos.response.PartyResponse;
import voter.election.voting_app.dtos.response.RegisterResponse;
import voter.election.voting_app.data.models.AppUser;
import voter.election.voting_app.data.repositories.CandidateRepository;
import voter.election.voting_app.data.models.Admin;
import voter.election.voting_app.data.repositories.AdminRepository;
import voter.election.voting_app.data.models.Party;
import voter.election.voting_app.data.repositories.VoterRepository;
import voter.election.voting_app.exception.AdminException;
import voter.election.voting_app.services.interfaces.AdminServices;

import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@Service
public class AdminServicesImpl implements AdminServices {

    private final AdminRepository adminRepository;
    private final CandidateRepository candidateRepository;
    private final PartyRepository partyRepository;
    private final VoterRepository voterRepository;
    @Override
    public RegisterResponse register(RegisterAdminRequest request) {
        AppUser appUser = new AppUser();

        appUser.setName(request.getName());
        appUser.setEmail(request.getEmail());
        appUser.setCreatedAt(LocalDateTime.now().toString());

        Admin admin = new Admin();
        admin.setUserDetails(appUser);
        admin.setEmployeeId(request.getEmployeeId());
        admin.setId(appUser.getId());
        Admin savedAdmin = adminRepository.save(admin);

        return getRegisterResponse(savedAdmin);
    }

    private RegisterResponse getRegisterResponse(Admin savedAdmin) {
        return RegisterResponse.builder()
                .id(savedAdmin.getId())
                .message("Admin registration successful")
                .successful(true)
                .build();
    }


    @Override
    public Admin getAdminById(long id) {
        return adminRepository.findById(id).orElseThrow(() ->
                new AdminException(String.format("Voter with  ID - %d is not foundAdmin not found", id)));

    }

    @Override
    public List<Party> viewParties() {
         return partyRepository.findAll();
    }

    @Override
    public PartyResponse deleteCandidateById(long id) {
        candidateRepository.deleteById(id);
        return new PartyResponse("Candidate with ID - %d successfully deleted", id);
    }

    @Override
    public PartyResponse deleteVoterById(long id) {
        voterRepository.deleteById(id);
        return new PartyResponse("Voter with ID - %d successfully deleted", id);
    }
}
