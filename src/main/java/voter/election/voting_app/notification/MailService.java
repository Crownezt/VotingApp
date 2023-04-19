package voter.election.voting_app.notification;


import voter.election.voting_app.dtos.request.EmailNotificationRequest;

public interface MailService {
    String sendHtmlMail(EmailNotificationRequest request);
}
