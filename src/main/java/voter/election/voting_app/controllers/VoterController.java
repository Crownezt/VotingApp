package voter.election.voting_app.controllers;

import com.github.fge.jsonpatch.JsonPatch;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voter.election.voting_app.data.models.Voter;
import voter.election.voting_app.dtos.request.VerifyUserRequest;
import voter.election.voting_app.dtos.request.CastVoteRequest;
import voter.election.voting_app.dtos.request.EmailNotificationRequest;
import voter.election.voting_app.dtos.request.LoginRequest;
import voter.election.voting_app.dtos.response.VoteCountResponse;
import voter.election.voting_app.dtos.response.VoteResponse;
import voter.election.voting_app.exception.ApiResponse;
import voter.election.voting_app.notification.MailService;
import voter.election.voting_app.services.interfaces.AppUserServices;
import voter.election.voting_app.services.interfaces.VoteServices;
import voter.election.voting_app.services.interfaces.VoterServices;
import voter.election.voting_app.dtos.request.RegisterVoterRequest;

import java.time.ZonedDateTime;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/voter")
public class VoterController {
    private final VoterServices voterServices;
    private final VoteServices voteServices;
    private final AppUserServices appUserServices;
    private final MailService mailService;

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<?> register(@Valid @ModelAttribute  RegisterVoterRequest registerVoterRequest) throws MessagingException {
        var registerResponse = voterServices.register(registerVoterRequest);
        return ResponseEntity.ok(registerResponse);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser (@RequestBody LoginRequest loginRequest,
                                        HttpServletRequest httpServletRequest){
        ApiResponse apiResponse=ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(appUserServices.login(loginRequest))
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMail(@RequestBody EmailNotificationRequest request){
        mailService.sendHtmlMail(request);
        return ResponseEntity.ok("Message sent successfully");
    }

    @PostMapping("/enable")
    public ResponseEntity<?> enableUser(@RequestBody VerifyUserRequest request){
        appUserServices.verifyUser(request);
        return ResponseEntity.ok("Enabled successfully");
    }

    @PostMapping("/cast_vote")
    ResponseEntity<?> castVote(@RequestBody CastVoteRequest request){
        VoteResponse voteResponse = voteServices.castVote(request);
        return new ResponseEntity<>(voteResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping("{voterId}")
    public ResponseEntity<?> getVoterById(@PathVariable long voterId) {
        Voter foundVoter = voterServices.getVoterById(voterId);
        return ResponseEntity.status(HttpStatus.OK).body(foundVoter);
    }

    @PatchMapping(value = "{voterId}", consumes = "application/json-patch+json")
     public ResponseEntity<?> updateVoter(@PathVariable long voterId, @RequestBody JsonPatch updatePayload) {
        try {
            var response = voterServices.updateVoter(voterId,updatePayload);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("allCandidates")
    public ResponseEntity<?> viewCandidates(){
        var response = voterServices.viewCandidates();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/count/{candidateId}")
    public ResponseEntity<VoteCountResponse> getVotesByCandidateId(@PathVariable long candidateId){
        VoteCountResponse response = voteServices.getVotesByCandidateId(candidateId);
        return ResponseEntity.ok(response);
       // return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping("/overall_vote")
    public ResponseEntity<?> countAllVote(){
        var response = voteServices.countAllVote();
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @GetMapping("/candidate_vote/{candidateId}")
    public ResponseEntity<VoteCountResponse> candidateVote(@PathVariable Long candidateId) {
        VoteCountResponse response = voteServices.getAllVotesByCandidateId(candidateId);
        return ResponseEntity.ok(response);
    }
}
