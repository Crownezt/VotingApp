package voter.election.voting_app.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import voter.election.voting_app.data.models.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByEmailIgnoreCase(String email);

}
