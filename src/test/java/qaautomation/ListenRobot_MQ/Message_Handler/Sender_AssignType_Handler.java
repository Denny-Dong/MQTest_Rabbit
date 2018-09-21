package qaautomation.ListenRobot_MQ.Message_Handler;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import qaautomation.ListenRobot_MQ.Environment.ExchangeQueue_Configuration;

@Component
public class Sender_AssignType_Handler {

    @Autowired
    ExchangeQueue_Configuration exchangeQueueConfiguration;

    @Autowired
    AmqpTemplate amqpTemplate;


    public void sender(String testData) {
        Message message = MessageBuilder.withBody(testData.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setContentEncoding("utf-8")
                .build();
        System.out.println("Sender ： " + message);
        this.amqpTemplate.convertAndSend(exchangeQueueConfiguration.getSmartcall_exchange(),
                exchangeQueueConfiguration.getAssign_tosmartcall_queue(), message);
    }
}
