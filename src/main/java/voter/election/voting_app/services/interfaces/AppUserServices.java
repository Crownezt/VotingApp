package voter.election.voting_app.services.interfaces;

import voter.election.voting_app.data.models.AppUser;
import voter.election.voting_app.dtos.request.VerifyUserRequest;
import voter.election.voting_app.dtos.request.LoginRequest;
import voter.election.voting_app.dtos.request.ResetPasswordRequest;
import voter.election.voting_app.dtos.response.LoginResponse;

public interface AppUserServices {
    LoginResponse login(LoginRequest loginRequest);
    String resetPassword(ResetPasswordRequest resetPasswordRequest);
    void saveUser (AppUser user);
    AppUser getByEmailAddress(String email);
    String forgotPassword(ResetPasswordRequest resetPasswordRequest);
    public void verifyUser(VerifyUserRequest request);
//    String generateToken (AppUser user);
}
