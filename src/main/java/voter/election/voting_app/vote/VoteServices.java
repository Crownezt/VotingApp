package voter.election.voting_app.vote;

import voter.election.voting_app.candidate.Candidate;
import voter.election.voting_app.vote.dtos.CastVoteRequest;
import voter.election.voting_app.vote.dtos.response.VoteCountResponse;
import voter.election.voting_app.vote.dtos.response.VoteResponse;

public interface VoteServices {

    public VoteResponse castVote(CastVoteRequest request);
    public VoteCountResponse getCandidateVote(Candidate candidateId);
    public VoteResponse countAllVote();
}
