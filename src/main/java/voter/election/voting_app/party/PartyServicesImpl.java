package voter.election.voting_app.party;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import voter.election.voting_app.candidate.dtos.RegisterResponse;

import java.util.List;
@AllArgsConstructor
@Service
public class PartyServicesImpl implements PartyServices {
    private final PartyRepository partyRepository;
    @Override
    public RegisterResponse register(String name) {
        Party party = new Party();
        party.setName(name);
        partyRepository.save(party);
        return null;
    }

    @Override
    public Party getCandidateById(long id) {
        return partyRepository.findById(id).orElseThrow(()->
                new PartyException("Party not found"));
    }

    @Override
    public List<Party> viewParties() {
        return partyRepository.findAll();
    }
}
