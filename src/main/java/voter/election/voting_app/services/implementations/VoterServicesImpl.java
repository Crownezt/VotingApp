package voter.election.voting_app.services.implementations;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import voter.election.voting_app.cloud_service.CloudService;
import voter.election.voting_app.data.models.*;
import voter.election.voting_app.data.repositories.CandidateRepository;
import voter.election.voting_app.data.repositories.PartyRepository;
import voter.election.voting_app.data.repositories.VoterRepository;
import voter.election.voting_app.dtos.request.EmailNotificationRequest;
import voter.election.voting_app.dtos.request.Recipient;
import voter.election.voting_app.dtos.request.Sender;
import voter.election.voting_app.dtos.response.RegisterResponse;
import voter.election.voting_app.exception.GenericException;
import voter.election.voting_app.exception.VoterException;
import voter.election.voting_app.dtos.request.RegisterVoterRequest;
import voter.election.voting_app.notification.MailService;
import voter.election.voting_app.services.interfaces.AppUserServices;
import voter.election.voting_app.services.interfaces.VoterServices;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class VoterServicesImpl implements VoterServices {
    private final VoterRepository voterRepository;
    private final CandidateRepository candidateRepository;
    private final PartyRepository partyRepository;
    private final MailService mailService;
    private final CloudService cloudService;

    @Override
    public RegisterResponse register(RegisterVoterRequest request) throws MessagingException {
        Optional<Voter> existingEmail = Optional.ofNullable(voterRepository.findByUserDetails_Email(request.getEmail()));
        Voter savedVoter;
        String jwt;
        if (existingEmail.isPresent()) {
            throw new VoterException("Email already exist");
        } else {
            AppUser appUser = new AppUser();
            appUser.setName(request.getName());
            appUser.setEmail(request.getEmail());
            appUser.setCreatedAt(LocalDateTime.now().toString());
            appUser.setPassword(hashPassword(request.getPassword()));

            Voter voter = new Voter();
            voter.setUserDetails(appUser);
            voter.setId(appUser.getId());
            if (request.getAge() >= 18) {
                voter.setAge(request.getAge());
            } else throw new VoterException("Age must be above 18");
            voter.setGender(request.getGender());
            voter.setId(appUser.getId());

            String imageUrl = cloudService.upload(request.getImage());
            appUser.setImageUrl(imageUrl);

            savedVoter = voterRepository.save(voter);

            jwt = Jwts.builder()
                    .setExpiration(Date.from(Instant.now().plusSeconds(84600)))
                    .setIssuer("voterz")
                    .setSubject(savedVoter.getUserDetails().getEmail())
                    .signWith(SignatureAlgorithm.HS256, "ybkjtnuybtfbyugnbryjnbrvtnbtchjbrcty")
                    .compact();


            EmailNotificationRequest emailNotificationRequest = new EmailNotificationRequest();
            emailNotificationRequest.getTo().add(new Recipient(appUser.getName(), appUser.getEmail()));
            emailNotificationRequest.setSender(new Sender("Voterz", "noreply@voterz.org"));
            emailNotificationRequest.setSubject("Welcome!");
            emailNotificationRequest.setTextContent(String.format(
                    "We appreciate your registration. " +
                            "\n please copy the copy token: %s" + jwt));
            String email = mailService.sendHtmlMail(emailNotificationRequest);

            if (email == null) {
                  throw new GenericException("Mail sending failed");
            }
        }
        return RegisterResponse.builder()
                .id(savedVoter.getId())
                .token(jwt)
                .successful(true)
                .message("Voter's registration successful")
                .build();
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public Voter getVoterById(long id) {
        return voterRepository.findById(id).orElseThrow(() ->
                new VoterException(String.format("Voter with  ID %d is not found", id)));
    }

    @Override
    public List<Voter> getVoter() {
        return voterRepository.findAll();
    }

    @Override
    public Voter updateVoter(long id, JsonPatch updatePayLoad) {
        ObjectMapper mapper = new ObjectMapper();
        Voter foundVoter = getVoterById(id);
        JsonNode node = mapper.convertValue(foundVoter, JsonNode.class);

        try{
            JsonNode updateNode = updatePayLoad.apply(node);

            Voter updatedVoter = mapper.convertValue(updateNode, Voter.class);
            return voterRepository.save(updatedVoter);
        }
        catch (JsonPatchException e) {
            log.error(e.getMessage());
            throw new RuntimeException();
        }
    }
    @Override
    public List<Candidate> viewCandidates() {
        return candidateRepository.findAll();
    }
    @Override
    public Optional<Candidate> viewCandidateById(long id){
        return candidateRepository.findById(id);
    }
    @Override
    public List<Party> viewParties() {
        return partyRepository.findAll();
    }

}