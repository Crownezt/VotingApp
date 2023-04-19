package voter.election.voting_app.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import voter.election.voting_app.dtos.request.RegisterAdminRequest;
import voter.election.voting_app.dtos.response.RegisterResponse;
import voter.election.voting_app.services.interfaces.AdminServices;

@AllArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminServices adminServices;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterAdminRequest request){
        RegisterResponse registerResponse = adminServices.register(request);
        return ResponseEntity.ok(registerResponse);
    }
}
