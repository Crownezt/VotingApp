package voter.election.voting_app.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import voter.election.voting_app.data.models.Office;

public interface OfficeRepository extends JpaRepository<Office, Long> {
}
