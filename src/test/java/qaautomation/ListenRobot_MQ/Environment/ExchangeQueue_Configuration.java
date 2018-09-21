package qaautomation.ListenRobot_MQ.Environment;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
@Getter
@Setter
public class ExchangeQueue_Configuration {
    private String assign_tosmartcall_queue;
    private String assign_torobotmanager_queue;
    private String call_tosmartcal_queue;
    private String smartcall_exchange;
    private String robotmanager_exchange;

    private Integer concurrency_count;
    private Integer firstConsumerId;
    private Integer callList_count;

    @Bean(name = "consumerIdList")
    List<Integer> consumerIdList() {
        List<Integer> consumerIdList = new ArrayList<>();
        for (int i = firstConsumerId; i < firstConsumerId + concurrency_count; i++) {
            consumerIdList.add(i);
        }
        return consumerIdList;
    }

}
