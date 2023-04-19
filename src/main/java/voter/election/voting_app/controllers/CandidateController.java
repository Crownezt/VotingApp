package voter.election.voting_app.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voter.election.voting_app.dtos.response.RegisterResponse;
import voter.election.voting_app.services.interfaces.CandidateServices;
import voter.election.voting_app.dtos.request.RegisterCandidateRequest;
import voter.election.voting_app.data.models.Candidate;

@AllArgsConstructor
@RestController
@RequestMapping("/api/candidate")
public class CandidateController {

    private final CandidateServices candidateServices;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterCandidateRequest request){
        RegisterResponse response = candidateServices.register(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCandidateById(@PathVariable long id){
        Candidate foundCandidate = candidateServices.getCandidateById(id);
        return ResponseEntity.status(HttpStatus.OK).body(foundCandidate);
    }
}
