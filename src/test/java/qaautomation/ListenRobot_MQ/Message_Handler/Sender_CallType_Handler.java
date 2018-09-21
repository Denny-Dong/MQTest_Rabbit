package qaautomation.ListenRobot_MQ.Message_Handler;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import qaautomation.ListenRobot_MQ.Environment.ExchangeQueue_Configuration;
import qaautomation.ListenRobot_MQ.Message_Factory.Flag_Statistic;

@Component
public class Sender_CallType_Handler {

    @Autowired
    ExchangeQueue_Configuration exchangeQueueConfiguration;

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    Flag_Statistic flagStatistic;


    public void sender(String testData) {
        Message message = MessageBuilder.withBody(testData.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setContentEncoding("utf-8")
                .build();
        System.out.println("Sender ： " + message);
        this.amqpTemplate.convertAndSend(exchangeQueueConfiguration.getSmartcall_exchange(),
                exchangeQueueConfiguration.getCall_tosmartcal_queue(), message);
        flagStatistic.setSender_CallType_Number(flagStatistic.getSender_CallType_Number() + 1);
    }
}
