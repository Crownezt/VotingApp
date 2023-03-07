package voter.election.voting_app.admin;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import voter.election.voting_app.admin.dtos.RegisterAdminRequest;
import voter.election.voting_app.admin.dtos.RegisterResponse;
import voter.election.voting_app.app_user.AppUser;
import voter.election.voting_app.candidate.Candidate;
import voter.election.voting_app.candidate.CandidateRepository;
import voter.election.voting_app.party.Party;
import voter.election.voting_app.voter.Voter;
import voter.election.voting_app.voter.VoterRepository;

import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@Service
public class AdminServicesImpl implements AdminServices {

    private final AdminRepository adminRepository;
    private final CandidateRepository candidateRepository;
    private final VoterRepository voterRepository;
    @Override
    public RegisterResponse register(RegisterAdminRequest request) {
        AppUser appUser = new AppUser();
        appUser.setFirstName(request.getFirstName());
        appUser.setLastName(request.getLastName());
        appUser.setOtherName(request.getOtherName());
        appUser.setEmail(request.getEmail());
        appUser.setCreatedAt(LocalDateTime.now().toString());

        Admin admin = new Admin();
        admin.setUserDetails(appUser);
        admin.setEmployeeId(request.getEmployeeId());
        Admin savedAdmin = adminRepository.save(admin);

        return getRegisterResponse(savedAdmin);
    }

    private RegisterResponse getRegisterResponse(Admin savedAdmin) {
        return RegisterResponse.builder()
                .id(savedAdmin.getId())
                .message("Admin registration successful")
                .successful(true)
                .code(HttpStatus.CREATED.value())
                .build();
    }


    @Override
    public Admin getAdminById(long id) {
        return adminRepository.findById(id).orElseThrow(() ->
                new AdminException(String.format("Voter with  ID - %d is not foundAdmin not found", id)));

    }

    @Override
    public List<Party> viewParties() {
        return null;
    }

    @Override
    public Candidate deleteCandidateById(long id) {
        candidateRepository.deleteById(id);
        return null;
    }

    @Override
    public Voter deleteVoterById(long id) {
        return null;
    }
}
