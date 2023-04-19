package voter.election.voting_app.dtos.request;


import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Recipient {
    private String name;
    private String email;
}
