package voter.election.voting_app.data.models;

import jakarta.persistence.*;
import lombok.*;
import voter.election.voting_app.Gender;
import voter.election.voting_app.exception.VoterException;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Voter {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;
   @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
   private AppUser userDetails;
   private Gender gender;
   private int age;
//   private String token;





}
