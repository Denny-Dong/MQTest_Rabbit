package qaautomation.ListenRobot_MQ.Message_Factory;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import qaautomation.ListenRobot_MQ.Environment.AIServer_Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class Start_Call_Factory {

    @Bean
    public List<String> startCallList(@Autowired @Qualifier(value = "consumerIdList") List<Integer> consumerIdList,
                                      @Autowired @Qualifier(value = "aiServerConfiguration") AIServer_Configuration aiServerConfiguration,
                                      @Autowired @Qualifier(value = "gatewayConfiguration") Gateway_Factory gatewayConfiguration) {

        List<String> startCallList = new ArrayList<>();

        for (Integer customerID :
                consumerIdList) {
            Map<String, Object> startCallMessage = new HashMap<>();

            //内部协商版本号
            startCallMessage.put("version", "1.0");
            startCallMessage.put("command", "start_call");
            startCallMessage.put("sequence", customerID);

            //废掉了
            startCallMessage.put("aimid", "788");
            startCallMessage.put("customer_id", String.valueOf(customerID));
            startCallMessage.put("enterprise_id", String.valueOf(customerID));

            //拨打计划ID
            startCallMessage.put("plan_id", 10050);

            //AI ID
            startCallMessage.put("robot_index", 0);

            //呼叫唯一ID consumerID-planID-被叫号码
            startCallMessage.put("call_id", "123-abc-123");
            startCallMessage.put("caller_id", gatewayConfiguration.getId());
            startCallMessage.put("callee", gatewayConfiguration.getCallee());

            //用户信息（名称，性别，称谓）
            startCallMessage.put("gender", "nan");
            startCallMessage.put("apellation", "wang");
            startCallMessage.put("name", "weichao");

            startCallMessage.put("area_code", "0571");

            //WEB
            startCallMessage.put("web_protocol", "http");
            startCallMessage.put("web_server", "192.168.123.231:90900");
            //ASR引擎
            startCallMessage.put("asr_engine", "keda");
            //本机语音模板路径
            startCallMessage.put("template_id", aiServerConfiguration.getTemplate_id());
            startCallMessage.put("template_path", "home/robotvoip/var/template/default");
            //编解码
            startCallMessage.put("audio_codec", "PCMA,PCMU");
            //录音配置 0不录音 1本地 2远端
            startCallMessage.put("audio_record", 1);
            startCallMessage.put("record_path", "/home/record/7892_10050_1017_1008");
            //是否录制早期媒体
            startCallMessage.put("early_media_record", 1);
            //AI Server
            startCallMessage.put("asr_server", aiServerConfiguration.getAsr());
            startCallMessage.put("nlp_server", aiServerConfiguration.getNlp());
            //语音模板参数
            Map<String, Object> options_map = new HashMap<>();
            options_map.put("option1", "1");
            options_map.put("option2", "2");
            options_map.put("option3", "3");
            startCallMessage.put("options", options_map);
            startCallList.add(new Gson().toJson(startCallMessage));
        }
        return startCallList;
    }


}
