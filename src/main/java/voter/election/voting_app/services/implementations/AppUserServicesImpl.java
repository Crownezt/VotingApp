package voter.election.voting_app.services.implementations;

import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import voter.election.voting_app.data.models.AppUser;
import voter.election.voting_app.data.repositories.AppUserRepository;
import voter.election.voting_app.dtos.request.VerifyUserRequest;
import voter.election.voting_app.dtos.request.LoginRequest;
import voter.election.voting_app.dtos.request.ResetPasswordRequest;
import voter.election.voting_app.dtos.response.LoginResponse;
import voter.election.voting_app.exception.GenericException;
import voter.election.voting_app.services.interfaces.AppUserServices;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AppUserServicesImpl implements AppUserServices {
    private final AppUserRepository appUserRepository;

    public LoginResponse login(LoginRequest loginRequest) {
        AppUser foundUser = appUserRepository.findByEmailIgnoreCase(loginRequest.getEmail());
        if (Objects.isNull(foundUser)) throw new GenericException("user does not exist");
        if (!foundUser.isVerified()) throw new GenericException("user has not been verified");
        try {
            if (!BCrypt.checkpw(loginRequest.getPassword(), foundUser.getPassword())) {
                throw new GenericException("password does not match");
            }
        } catch (GenericException e) {
            throw new RuntimeException(e);
        }
        return LoginResponse.builder()
                .message("Login Successful")
                .loginSuccess(true)
                .build();

    }

    @Override
    public String resetPassword(ResetPasswordRequest resetPasswordRequest) {
        AppUser user = appUserRepository.findByEmailIgnoreCase
                (resetPasswordRequest.getEmailAddress());
        if (Objects.isNull(user)) throw new GenericException("invalid details");

        if (!BCrypt.checkpw(resetPasswordRequest.getOldPassword(), user.getPassword())) {
            throw new GenericException("invalid details");
        }
        if (!resetPasswordRequest.getNewPassword().equals(resetPasswordRequest.getConfirmPassword()))
            throw new GenericException("password do not match");
        user.setPassword(hashPassword(resetPasswordRequest.getNewPassword()));
        saveUser(user);
        return "password reset successful";
    }

    @Override
    public String forgotPassword(ResetPasswordRequest resetPasswordRequest) {
        AppUser user = getByEmailAddress(resetPasswordRequest.getEmailAddress());
        if (Objects.isNull(user)) throw new GenericException("user with this email does not exist");

        if (!resetPasswordRequest.getNewPassword().equals(resetPasswordRequest.getConfirmPassword()))
            throw new GenericException("password do not match");
        user.setPassword(hashPassword(resetPasswordRequest.getNewPassword()));
        saveUser(user);
        return "Forgot Password Resolved Successful.";
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public void saveUser(AppUser user) {
        appUserRepository.save(user);
    }

    @Override
    public AppUser getByEmailAddress(String email) {
        return appUserRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public void verifyUser(VerifyUserRequest request) {
        boolean isSigned = Jwts.parser()
                .isSigned(request.getToken());
        if (isSigned) {
            AppUser foundEmail = appUserRepository.findByEmailIgnoreCase(request.getEmail());
            if (Objects.isNull(foundEmail)) throw new GenericException("invalid email");
            foundEmail.setVerified(true);
            appUserRepository.save(foundEmail);
        } else throw new GenericException("Unable to generate token");
    }


}
