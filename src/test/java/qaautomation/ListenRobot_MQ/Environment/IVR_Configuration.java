package qaautomation.ListenRobot_MQ.Environment;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import qaautomation.ListenRobot_MQ.Message_Factory.Gateway_Factory;

@Configuration(value = "ivrGateway")
@ConfigurationProperties("ivr.gw")
@Setter
@Getter
public class IVR_Configuration extends Gateway_Factory {

    @Bean(name = "gatewayConfiguration")
    @Profile("ivrGW")
    Gateway_Factory getIvrGatewayConfiguration() {
        return super.getGatewayConfiguration();
    }


}
