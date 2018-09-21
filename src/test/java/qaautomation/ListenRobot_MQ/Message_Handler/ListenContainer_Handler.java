package qaautomation.ListenRobot_MQ.Message_Handler;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import qaautomation.ListenRobot_MQ.Environment.ExchangeQueue_Configuration;

@Configuration
public class ListenContainer_Handler {

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             Receiver_Handler robotConsumer, @Autowired
                                                     ExchangeQueue_Configuration rabbitMQDefine) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(rabbitMQDefine.getAssign_torobotmanager_queue());
        container.setMessageListener(robotConsumer);
        return container;
    }

}
