package qaautomation.ListenRobot_MQ.Environment;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component(value = "aiServerConfiguration")
@ConfigurationProperties(prefix = "ai.server")
@Getter
@Setter
public class AIServer_Configuration {
    private String nlp;
    private String asr;
    private String template_id;
}
