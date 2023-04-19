package voter.election.voting_app.services.implementations;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import voter.election.voting_app.data.models.*;
import voter.election.voting_app.dtos.response.RegisterResponse;
import voter.election.voting_app.exception.CandidateException;
import voter.election.voting_app.dtos.request.RegisterCandidateRequest;
import voter.election.voting_app.data.repositories.CandidateRepository;
import voter.election.voting_app.data.repositories.PartyRepository;
import voter.election.voting_app.exception.VoterException;
import voter.election.voting_app.services.interfaces.CandidateServices;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Data
@Slf4j
@Service
public class CandidateServicesImpl implements CandidateServices {

    private final CandidateRepository candidateRepository;
    private final PartyRepository partyRepository;

    @Override
    public RegisterResponse register(RegisterCandidateRequest request) {
        Optional<Candidate> existingEmail = Optional.ofNullable(candidateRepository.findByUserDetails_Email(request.getEmail()));
        Voter savedVoter;
        if (existingEmail.isPresent()) {
            throw new VoterException("Email already exist");
        }
        AppUser appUser = new AppUser();
        appUser.setName(request.getName());
        appUser.setEmail(request.getEmail());
        appUser.setCreatedAt(LocalDateTime.now().toString());

        Candidate candidate = new Candidate();
        Office office = new Office();
        candidate.setOffice(office.getName());
        candidate.setUserDetails(appUser);
        candidate.setGender(request.getGender());
        candidate.setId(appUser.getId());
        candidate.setPhoneNumber(request.getPhoneNumber());
        candidate.setParty(request.getParty());
        appUser.setPassword(request.getPassword());

        if (request.getAge() >= 18) {
            candidate.setAge(request.getAge());
        } else throw new VoterException("Age must be above 18");

        Candidate savedCandidate = candidateRepository.save(candidate);
        return getRegisterResponse(savedCandidate);
    }

    private RegisterResponse getRegisterResponse(Candidate savedCandidate) {
        return RegisterResponse.builder()
                                .id(savedCandidate.getId())
                                .message("Candidate's registration successful")
                                .successful(true)
                                .build();
    }

    @Override
    public Candidate getCandidateById(long id) {
        return candidateRepository.findById(id).orElseThrow(()->
                new CandidateException("Candidate not found"));
    }

    @Override
    public Candidate updateCandidate(long id, JsonPatch updatePayLoad) {
        ObjectMapper mapper = new ObjectMapper();
        Candidate foundCandidate = getCandidateById(id);
        JsonNode node = mapper.convertValue(foundCandidate, JsonNode.class);
        try {
            JsonNode updateNode = updatePayLoad.apply(node);
            Candidate updatedCandidate = mapper.convertValue(updateNode, Candidate.class);
            return updatedCandidate;
        } catch (JsonPatchException e) {
            log.error(e.getMessage());
            throw new CandidateException("Candidate with ID %d not found");
        }
    }
    @Override
    public List<Party> viewParties() {
        return partyRepository.findAll();
    }
}
