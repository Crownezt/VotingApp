package voter.election.voting_app.services.implementations;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import voter.election.voting_app.cloud_service.CloudService;
import voter.election.voting_app.data.repositories.PartyRepository;
import voter.election.voting_app.dtos.request.EmailNotificationRequest;
import voter.election.voting_app.dtos.request.InviteAdminRequest;
import voter.election.voting_app.dtos.request.Recipient;
import voter.election.voting_app.dtos.request.Sender;
import voter.election.voting_app.dtos.response.PartyResponse;
import voter.election.voting_app.dtos.response.RegisterResponse;
import voter.election.voting_app.data.models.AppUser;
import voter.election.voting_app.data.repositories.CandidateRepository;
import voter.election.voting_app.data.models.Admin;
import voter.election.voting_app.data.repositories.AdminRepository;
import voter.election.voting_app.data.models.Party;
import voter.election.voting_app.data.repositories.VoterRepository;
import voter.election.voting_app.exception.AdminException;
import voter.election.voting_app.exception.GenericException;
import voter.election.voting_app.notification.MailService;
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
    private final CloudService cloudService;
    private final MailService mailService;
    @Override
    public RegisterResponse register(InviteAdminRequest request) {
        AppUser appUser = new AppUser();

        appUser.setName(request.getName());
        appUser.setEmail(request.getEmail());
        appUser.setCreatedAt(LocalDateTime.now().toString());
        appUser.setPassword(hashPassword(request.getPassword()));
        String imageUrl = cloudService.upload(request.getImage());
        appUser.setImageUrl(imageUrl);

        Admin admin = new Admin();
        admin.setUserDetails(appUser);
        admin.setEmployeeId(request.getEmployeeId());
        admin.setId(appUser.getId());

        Admin savedAdmin = adminRepository.save(admin);

            EmailNotificationRequest emailNotificationRequest = new EmailNotificationRequest();
            emailNotificationRequest.getTo().add(new Recipient(appUser.getName(), appUser.getEmail()));
            emailNotificationRequest.setSender(new Sender("Voter", "noreply@voterz.org"));
            emailNotificationRequest.setSubject("Invitation!");
            emailNotificationRequest.setTextContent("Welcome to Voterz, " +
                    "\n This is an invitation to become an admin.");

            String email = mailService.sendHtmlMail(emailNotificationRequest);
        if(email == null) {
            throw new GenericException("Mail sending failed");
        }
        return getRegisterResponse(savedAdmin);
    }
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
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
