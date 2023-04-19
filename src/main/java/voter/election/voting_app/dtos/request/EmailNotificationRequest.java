package voter.election.voting_app.dtos.request;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailNotificationRequest {
    private  Sender sender;
    private List<Recipient> to=new ArrayList<>();
    private  String subject;
    private String textContent;


}
