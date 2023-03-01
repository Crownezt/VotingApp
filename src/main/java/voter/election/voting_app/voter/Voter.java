package voter.election.voting_app.voter;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import voter.election.voting_app.app_user.AppUser;
import voter.election.voting_app.Gender;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Voter {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;
   @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
   private AppUser userDetails;
   private Gender gender;
   private int age;

}
