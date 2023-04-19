package voter.election.voting_app.dtos.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Sender {
    private String name;
    private String email;
}
