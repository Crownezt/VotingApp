package voter.election.voting_app.voter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import voter.election.voting_app.app_user.AppUser;
import voter.election.voting_app.candidate.Candidate;
import voter.election.voting_app.party.Party;
import voter.election.voting_app.voter.dtos.RegisterResponse;
import voter.election.voting_app.voter.dtos.RegisterVoterRequest;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class VoterServicesImpl implements VoterServices {
    private VoterRepository voterRepository;

    @Override
    public RegisterResponse register(RegisterVoterRequest request) {
        AppUser appUser = new AppUser();
        appUser.setFirstName(request.getFirstName());
        appUser.setLastName(request.getLastName());
        appUser.setOtherName(request.getOtherName());
        appUser.setEmail(request.getEmail());
        appUser.setCreatedAt(LocalDateTime.now().toString());

        Voter voter = new Voter();
        voter.setUserDetails(appUser);
        voter.setAge(request.getAge());
        voter.setGender(request.getGender());

        Voter savedVoter = voterRepository.save(voter);

        return getRegisterResponse(savedVoter);
    }

    private Voter getVoterById(long id) {
        return voterRepository.findById(id).orElseThrow(() ->
                new VoterException(String.format("Voter with  ID %d is not found", id)));
    }

    @Override
    public Voter updateProfile(long id, JsonPatch updatePayLoad) {
        ObjectMapper mapper = new ObjectMapper();
        Voter foundVoter = getVoterById(id);
        JsonNode node = mapper.convertValue(foundVoter, JsonNode.class);

        try{
            JsonNode updateNode = updatePayLoad.apply(node);

            Voter updatedVoter = mapper.convertValue(updateNode, Voter.class);
            voterRepository.save(updatedVoter);
            return updatedVoter;
        }
        catch (JsonPatchException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Voter checkStatus(long id) {
        return getVoterById(id);
    }


    @Override
    public List<Candidate> viewCandidates() {
        return null;
    }

    @Override
    public List<Party> viewParties() {
        return null;
    }

    private RegisterResponse getRegisterResponse(Voter savedVoter) {
        return RegisterResponse.builder()
                .code(HttpStatus.CREATED.value())
                .id(savedVoter.getId())
                .message("Voter's registration successful")
                .successful(true)
                .build();
    }
}