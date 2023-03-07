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
   @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
   private AppUser userDetails;
   private Gender gender;
   private int age;

   public void setAge(int age) {
      if (age >= 18) {
         this.age = age;
      }
      throw new VoterException("Age must not be below 18");
   }
}
