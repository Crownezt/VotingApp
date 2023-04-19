package voter.election.voting_app.config.mail;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MailConfig {
    private String apiKey;
    private String mailUrl;
}
