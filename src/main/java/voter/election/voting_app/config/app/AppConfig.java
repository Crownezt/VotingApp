package voter.election.voting_app.config.app;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import voter.election.voting_app.config.mail.MailConfig;
import voter.election.voting_app.notification.MailService;
import voter.election.voting_app.notification.MailServiceImpl;

@Configuration
public class AppConfig {
    @Value("${cloudinary.api.name}")
    private String cloudName;
    @Value("${cloudinary.api.key}")
    private String apiKey;
    @Value("${cloudinary.api.secret}")
    private String apiSecret;
    @Value("${mail.api.key}")
    private String mailApiKey;
    @Value("${sendinblue.mail.url}")
    private String mailUrl;

    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name",cloudName,
                        "api_key",apiKey,
                        "api_secret",apiSecret
                )
        );
    }
    @Bean
    public MailConfig mailConfig() {
        return new MailConfig(mailApiKey, mailUrl);
    }
}