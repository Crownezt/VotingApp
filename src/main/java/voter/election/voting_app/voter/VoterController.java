package voter.election.voting_app.voter;

import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.google.gson.JsonIOException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voter.election.voting_app.voter.dtos.RegisterResponse;
import voter.election.voting_app.voter.dtos.RegisterVoterRequest;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/voter")
public class VoterController {
    private final VoterServices voterServices;

    @PostMapping
    ResponseEntity<?> register(@RequestBody RegisterVoterRequest registerVoterRequest){
        RegisterResponse registerResponse = voterServices.register(registerVoterRequest);
        return ResponseEntity.status(registerResponse.getCode()).body(registerResponse);
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

    public ResponseEntity<?> viewCandidates(){
        var response = voterServices.viewCandidates();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
