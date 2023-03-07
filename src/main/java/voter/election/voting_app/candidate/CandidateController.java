package voter.election.voting_app.candidate;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voter.election.voting_app.candidate.dtos.RegisterCandidateRequest;
import voter.election.voting_app.candidate.dtos.RegisterResponse;

@AllArgsConstructor
@RestController
@RequestMapping("/api/candidate")
public class CandidateController {

    private final CandidateServices candidateServices;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterCandidateRequest request){
        RegisterResponse response = candidateServices.register(request);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCandidateById(@PathVariable long id){
        Candidate foundCandidate = candidateServices.getCandidateById(id);
        return ResponseEntity.status(HttpStatus.OK).body(foundCandidate);
    }
}
