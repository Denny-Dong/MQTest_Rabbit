package qaautomation.ListenRobot_MQ.Message_Handler;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import qaautomation.ListenRobot_MQ.Message_Factory.Flag_Statistic;

import java.util.concurrent.CountDownLatch;

@Component
@Setter
@Getter
public class Receiver_Handler implements MessageListener {

    CountDownLatch countDownLatch = new CountDownLatch(400);
    @Autowired
    Flag_Statistic statisticsCount;
    private String robot_consumed_message;

    @Override
    public void onMessage(Message message) {
        this.robot_consumed_message = new String(message.getBody());
        System.out.println("Robot_Consumer received : " + robot_consumed_message);
        JsonObject jsonObject = new JsonParser().parse(this.robot_consumed_message).getAsJsonObject();
        String consumerType = jsonObject.get("call_static").getAsJsonObject().get("flag").getAsString();
        if (consumerType.equals("A")) {
            statisticsCount.setATypeConsumer(statisticsCount.getATypeConsumer() + 1);
        } else if (consumerType.equals("B")) {
            statisticsCount.setBTypeConsumer(statisticsCount.getBTypeConsumer() + 1);
        } else if (consumerType.equals("C")) {
            statisticsCount.setCTypeConsumer(statisticsCount.getCTypeConsumer() + 1);
        } else if (consumerType.equals("D")) {
            statisticsCount.setDTypeConsumer(statisticsCount.getDTypeConsumer() + 1);
        } else if (consumerType.equals("E")) {
            statisticsCount.setETypeConsumer(statisticsCount.getETypeConsumer() + 1);
        } else {
            statisticsCount.setUnknownTypeConsumer(statisticsCount.getUnknownTypeConsumer() + 1);
        }
        this.countDownLatch.countDown();
    }
}
