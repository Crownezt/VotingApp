package voter.election.voting_app.services.interfaces;

import voter.election.voting_app.dtos.request.CastVoteRequest;
import voter.election.voting_app.dtos.request.LoginRequest;
import voter.election.voting_app.dtos.response.VoteCountResponse;
import voter.election.voting_app.dtos.response.VoteResponse;

public interface VoteServices {

    public VoteResponse castVote(CastVoteRequest request);
    public VoteCountResponse getVotesByCandidateId(long candidateId);
    VoteCountResponse countAllVote();
    VoteCountResponse getVotesByDate(String date);
    VoteCountResponse getAllVotesByCandidateId(Long candidateId);

}