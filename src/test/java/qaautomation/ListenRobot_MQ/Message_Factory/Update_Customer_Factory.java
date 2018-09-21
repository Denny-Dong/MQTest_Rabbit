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
public class Update_Customer_Factory {


    @Bean
    List<String> update_customer_list(
            @Autowired @Qualifier(value = "gatewayConfiguration") Gateway_Factory gw_define,
            @Autowired @Qualifier(value = "consumerIdList") List<Integer> consumerIdList) {

        List<String> update_customer_list = new ArrayList<>();
        for (Integer consumerId :
                consumerIdList) {
            Map<String, Object> agent_map = new HashMap<>();
            agent_map.put("name", "alice");
            agent_map.put("number", "1008");
            agent_map.put("remote_number", "13712341234");
            agent_map.put("password", "123456");
            agent_map.put("cid", "057186831234");
            agent_map.put("type", "nomarl_extension");
            agent_map.put("call_direction", "outgoing");

            List<Map<String, Object>> agent_list = new ArrayList<>();
            agent_list.add(agent_map);

            Map<String, Object> caller_map = new HashMap<>();
            caller_map.put("id", gw_define.getId());
            caller_map.put("gateway_number", gw_define.getNumber());
            caller_map.put("sip_server", gw_define.getSip_server());
            caller_map.put("sip_user_name", gw_define.getSip_user_name());
            caller_map.put("sip_password", gw_define.getSip_password());
            caller_map.put("sip_register", Boolean.valueOf(gw_define.getSip_register()));
            caller_map.put("capacity", Integer.parseInt(gw_define.getCapacity()));
            caller_map.put("router", gw_define.getRouter());

            List<Map<String, Object>> caller_list = new ArrayList();
            caller_list.add(caller_map);

            HashMap<String, Object> update_customer_map = new HashMap<>();
            update_customer_map.put("version", "1.0");
            update_customer_map.put("command", "update_customer");
            update_customer_map.put("sequence", 56645);
            update_customer_map.put("enterprise_id", String.valueOf(consumerId));
            update_customer_map.put("customer_id", String.valueOf(consumerId));
            update_customer_map.put("agent_list", agent_list);
            update_customer_map.put("caller_list", caller_list);
            update_customer_map.put("robot_total", 10);

            update_customer_list.add(new Gson().toJson(update_customer_map));
        }
        return update_customer_list;
    }


}
