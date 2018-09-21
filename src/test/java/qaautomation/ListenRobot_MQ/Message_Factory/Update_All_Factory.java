package qaautomation.ListenRobot_MQ.Message_Factory;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class Update_All_Factory {


    @Bean
    String update_all_map(@Autowired @Qualifier(value = "consumerIdList") List<Integer> consumerIdList) {
        List<Map<String, Object>> customer_list = new ArrayList<>();
        for (Integer consumerId :
                consumerIdList) {
            Map<String, Object> customerMap = new HashMap<>();
            customerMap.put("id", String.valueOf(consumerId));
            customerMap.put("name", "Denny_" + consumerId);
            customerMap.put("enterprise_id", String.valueOf(consumerId));
            customer_list.add(customerMap);
        }
        HashMap<String, Object> update_all_map = new HashMap<>();
        update_all_map.put("version", "1.0");
        update_all_map.put("command", "update_all");
        update_all_map.put("sequence", 500);
        update_all_map.put("customer_list", customer_list);
        return new Gson().toJson(update_all_map);
    }


}
