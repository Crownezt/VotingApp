package voter.election.voting_app.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import voter.election.voting_app.data.models.Candidate;
import voter.election.voting_app.data.models.Vote;
import voter.election.voting_app.data.models.Voter;
import voter.election.voting_app.data.repositories.CandidateRepository;
import voter.election.voting_app.data.repositories.VoteRepository;
import voter.election.voting_app.data.repositories.VoterRepository;
import voter.election.voting_app.dtos.request.CastVoteRequest;
import voter.election.voting_app.dtos.request.LoginRequest;
import voter.election.voting_app.dtos.response.VoteCountResponse;
import voter.election.voting_app.dtos.response.VoteResponse;
import voter.election.voting_app.exception.VoterException;
import voter.election.voting_app.services.interfaces.AppUserServices;
import voter.election.voting_app.services.interfaces.VoteServices;
import voter.election.voting_app.services.interfaces.VoterServices;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class VoteServicesImpl implements VoteServices {

    private final VoteRepository voteRepository;
    private final VoterRepository voterRepository;
    private final CandidateRepository candidateRepository;
//    private  final AppUserServices appUserServices;
//    private final VoterServices voterServices;


    @Override
    public VoteResponse castVote(CastVoteRequest request) {

        Voter foundVoter = voterRepository.findById(request.getVoterId()).orElseThrow(()-> new VoterException("Voter not found"));

        Candidate foundCandidate = candidateRepository.findById(request.getCandidateId()).orElseThrow(()-> new VoterException("Candidate not found"));


        Optional<Vote> foundVote = voteRepository.findVoteByCandidate_IdAndVoter_Id(request.getCandidateId(), request.getVoterId());
        Vote vote = new Vote();

//        if (!voterRepository.existsById(request.getVoterId())) {
//            throw new VoterException("Invalid voter, Please enter valid credentials");
//        }
//        if (!candidateRepository.existsById(request.getCandidateId())) {
//            throw new VoterException("Invalid candidate");
//        }
        if (foundVote.isPresent()) {
            throw new VoterException("Multiple voting is not allowed");
        }
        else {
            vote.setVoter(foundVoter);
            vote.setCandidate(foundCandidate);
            vote.setCreatedAt(LocalDateTime.now().toString());
            Vote savedVote = voteRepository.save(vote);
            return new VoteResponse("Voting Successfully with candidate Id", savedVote.getCandidate().getId());
        }
    }

//    private Voter getVoterById(Voter id) {
//        return voterRepository.findById(id.getId()).orElseThrow(() ->
//                new VoterException(String.format("Voter with  ID %s is not found", id)));
//    }

    @Override
    public VoteCountResponse getVotesByCandidateId(long candidateId) {
        List<Vote> voteCount = voteRepository.findAllByCandidate_Id(candidateId);
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(()-> new VoterException("not found"));

        return VoteCountResponse.builder()
                                .message("Total vote for")
                                .candidateName(candidate.getUserDetails().getName())
                                .voteCount(voteCount.size())
                                .build();
    }

    @Override
    public VoteCountResponse countAllVote() {
        List<Vote> overallVote = voteRepository.findAll();
        return  VoteCountResponse.builder()
                                .message("Total vote")
                                .voteCount(overallVote.size())
                                .build();
    }

    @Override
    public VoteCountResponse getVotesByDate(String date) {
        List<Vote> response = voteRepository.findAllByCreatedAt(date);
        return VoteCountResponse.builder()
                                .message(String.format("Total votes casted on %s", date))
                                .voteCount(response.size())
                                .build();
    }

    @Override
    public VoteCountResponse getAllVotesByCandidateId(Long candidateId) {
        List<Vote> candidateVotes = voteRepository.findAllByCandidate_Id(candidateId);
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(()-> new VoterException("not found"));

        return VoteCountResponse.builder()
                .message("Total vote for")
                .candidateName(candidate.getUserDetails().getName())
                .voteCount(candidateVotes.size())
                .build();
    }
}
