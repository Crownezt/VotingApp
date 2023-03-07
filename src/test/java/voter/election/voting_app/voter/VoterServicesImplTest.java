package voter.election.voting_app.voter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import voter.election.voting_app.voter.dtos.RegisterResponse;
import voter.election.voting_app.voter.dtos.RegisterVoterRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import static voter.election.voting_app.Gender.MALE;

@SpringBootTest
class VoterServicesImplTest {
    @Autowired
    private VoterServices voterServices;
    private RegisterVoterRequest registerVoterRequest;

    @BeforeEach
    void setUp(){
        registerVoterRequest = new RegisterVoterRequest();
        registerVoterRequest.setFirstName("Ade");
        registerVoterRequest.setLastName("Wunmi");
        registerVoterRequest.setOtherName("Obi");
        registerVoterRequest.setAge(21);
        registerVoterRequest.setEmail("adewunmi@staygreat.com");
        registerVoterRequest.setGender(MALE);
    }

    @Test
    void registerTest(){
        RegisterResponse registerResponse = voterServices.register(registerVoterRequest);
        assertThat(registerResponse).isNotNull();
        assertThat(registerResponse.getCode()).isEqualTo(HttpStatus.CREATED.value());
    }

}