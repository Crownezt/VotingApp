package voter.election.voting_app.vote;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import voter.election.voting_app.candidate.Candidate;
import voter.election.voting_app.vote.dtos.CastVoteRequest;
import voter.election.voting_app.vote.dtos.response.VoteCountResponse;
import voter.election.voting_app.vote.dtos.response.VoteResponse;
import voter.election.voting_app.voter.Voter;
import voter.election.voting_app.voter.VoterException;
import voter.election.voting_app.voter.VoterRepository;

@AllArgsConstructor
@Service
public class VoteServicesImpl implements VoteServices{

    private final VoteRepository voteRepository;
    private final VoterRepository voterRepository;
    @Override
    public VoteResponse castVote(CastVoteRequest request) {
        Vote vote = new Vote();

        if (request.getVoterId() == getVoterById(request.getVoterId())) {
            vote.setVoterId(request.getVoterId());
            vote.setCandidateId(request.getCandidateId());
            Vote savedVote = voteRepository.save(vote);
            return new VoteResponse("Successfully voted candidate with ID %d", savedVote.getCandidateId());

        } else throw new VoterException("Voter must not be below 18 years");
    }

    private Voter getVoterById(Voter id) {
        return voterRepository.findById(id.getId()).orElseThrow(() ->
                new VoterException(String.format("Voter with  ID %s is not found", id)));
    }

    @Override
    public VoteCountResponse getCandidateVote(Candidate candidateId) {
        long voteCount = voteRepository.countVotesByCandidateId(candidateId);
        return new VoteCountResponse("Candidate %d has %d votes", candidateId.getId(), voteCount);
    }

    @Override
    public VoteResponse countAllVote() {

        return null;
    }
}
