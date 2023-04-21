package voter.election.voting_app.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voter.election.voting_app.dtos.request.EmailNotificationRequest;
import voter.election.voting_app.dtos.request.LoginRequest;
import voter.election.voting_app.dtos.response.RegisterResponse;
import voter.election.voting_app.exception.ApiResponse;
import voter.election.voting_app.notification.MailService;
import voter.election.voting_app.services.interfaces.AppUserServices;
import voter.election.voting_app.services.interfaces.CandidateServices;
import voter.election.voting_app.dtos.request.RegisterCandidateRequest;
import voter.election.voting_app.data.models.Candidate;

import java.time.ZonedDateTime;

@AllArgsConstructor
@RestController
@RequestMapping("/api/candidate")
public class CandidateController {

    private final CandidateServices candidateServices;
    private final AppUserServices appUserServices;
    private final MailService mailService;



    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterCandidateRequest request){
        RegisterResponse response = candidateServices.register(request);
        return ResponseEntity.ok(response);
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
    public ResponseEntity<?> sendMail(@RequestBody EmailNotificationRequest request) {
        mailService.sendHtmlMail(request);
        return ResponseEntity.ok("Message sent successfully");
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCandidateById(@PathVariable long id){
        Candidate foundCandidate = candidateServices.getCandidateById(id);
        return ResponseEntity.status(HttpStatus.OK).body(foundCandidate);
    }
}
