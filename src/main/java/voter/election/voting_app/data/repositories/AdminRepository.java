package voter.election.voting_app.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import voter.election.voting_app.data.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
