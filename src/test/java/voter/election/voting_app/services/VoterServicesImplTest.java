package voter.election.voting_app.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import voter.election.voting_app.dtos.response.RegisterResponse;
import voter.election.voting_app.dtos.request.RegisterVoterRequest;
import voter.election.voting_app.services.interfaces.VoterServices;

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
        registerVoterRequest.setName("Peter Obi");
        registerVoterRequest.setAge(21);
        registerVoterRequest.setEmail("adewunmi@staygreat.com");
        registerVoterRequest.setGender(MALE);
    }

    @Test
    void registerTest(){
        RegisterResponse registerResponse = voterServices.register(registerVoterRequest);
        assertThat(registerResponse).isNotNull();
        assertThat(registerResponse.isSuccessful()).isEqualTo(true);
    }

}