package qaautomation.ListenRobot_MQ.Scenario;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import qaautomation.ListenRobot_MQ.Environment.ExchangeQueue_Configuration;
import qaautomation.ListenRobot_MQ.Message_Factory.Flag_Statistic;
import qaautomation.ListenRobot_MQ.Message_Handler.Receiver_Handler;
import qaautomation.ListenRobot_MQ.Message_Handler.Sender_AssignType_Handler;
import qaautomation.ListenRobot_MQ.Message_Handler.Sender_CallType_Handler;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SingleTenement_Scenario {

    @Autowired
    List<Integer> consumerIdList;
    @Autowired
    Flag_Statistic statisticsCount;
    @Autowired
    Sender_CallType_Handler senderCallTypeHandler;
    @Autowired
    Receiver_Handler consumer;
    @Autowired
    @Qualifier(value = "update_all_map")
    String update_all_map;
    @Autowired
    @Qualifier(value = "update_customer_list")
    List<String> update_customer_list;
    @Autowired
    @Qualifier(value = "startCallList")
    List<String> startCallList;
    @Autowired
    Sender_AssignType_Handler senderAssignTypeHandler;
    @Autowired
    ExchangeQueue_Configuration exchangeQueueConfiguration;

    @Test
    public void SingleTenement_Scenario_1_update_all_info() {
        senderAssignTypeHandler.sender(update_all_map);
    }

    @Test
    public void SingleTenement_Scenario_2_update_customer_info() {
        for (String update_customer_string :
                update_customer_list) {
            senderAssignTypeHandler.sender(update_customer_string);
        }
    }

    @Test
    public void SingleTenement_Scenario_3_start_call() throws InterruptedException {
        String message = startCallList.get(new Random().nextInt(startCallList.size()));
        for (int i = 0; i < exchangeQueueConfiguration.getCallList_count(); i++) {
            senderCallTypeHandler.sender(message);
        }

        consumer.getCountDownLatch().await(15, TimeUnit.SECONDS);
//        JsonParser jsonParser = new JsonParser();
//        JsonObject jsonObject = jsonParser.parse(consumer.getRobot_consumed_message()).getAsJsonObject();
//        Assert.assertThat(jsonObject.get("call_static").getAsJsonObject().get("callee").getAsString(), Is.is
// ("66666"));
        statisticsCount.printResult(consumerIdList);
    }


}
