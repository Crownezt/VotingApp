package voter.election.voting_app.candidate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import voter.election.voting_app.app_user.AppUser;
import voter.election.voting_app.candidate.dtos.RegisterCandidateRequest;
import voter.election.voting_app.office.Office;
import voter.election.voting_app.party.Party;
import voter.election.voting_app.party.PartyRepository;
import voter.election.voting_app.candidate.dtos.RegisterResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Data
@Slf4j
@Service
public class CandidateServicesImpl implements CandidateServices{

    private final CandidateRepository candidateRepository;
    private final PartyRepository partyRepository;

    @Override
    public RegisterResponse register(RegisterCandidateRequest request) {
        AppUser appUser = new AppUser();
        appUser.setFirstName(request.getFirstName());
        appUser.setLastName(request.getLastName());
        appUser.setOtherName(request.getOtherName());
        appUser.setEmail(request.getEmail());
        appUser.setCreatedAt(LocalDateTime.now().toString());

        Candidate candidate = new Candidate();
        Office office = new Office();
        candidate.setOffice(office.getName());
        candidate.setUserDetails(appUser);
        candidate.setGender(request.getGender());
        candidate.setPhoneNumber(request.getPhoneNumber());
        candidate.setParty(request.getParty());

        Candidate savedCandidate = candidateRepository.save(candidate);
        return getRegisterResponse(savedCandidate);
    }

    private RegisterResponse getRegisterResponse(Candidate savedCandidate) {
        return RegisterResponse.builder()
                                .id(savedCandidate.getId())
                                .message("Candidate's registration successful")
                                .code(HttpStatus.CREATED.value())
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
        try{
            JsonNode updateNode = updatePayLoad.apply(node);
            var updateCandidate = mapper.convertValue(updateNode, Candidate.class);
            return updateCandidate;
        }catch(JsonPatchException e){
             log.error(e.getMessage());
             throw new CandidateException("Candidate ");
        }
    }
    @Override
    public List<Party> viewParties() {
        return partyRepository.findAll();
    }
}
