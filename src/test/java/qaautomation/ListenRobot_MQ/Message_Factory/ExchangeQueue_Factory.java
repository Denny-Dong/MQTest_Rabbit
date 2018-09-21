package qaautomation.ListenRobot_MQ.Message_Factory;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import qaautomation.ListenRobot_MQ.Environment.ExchangeQueue_Configuration;

@Component
public class ExchangeQueue_Factory {

    @Autowired
    ExchangeQueue_Configuration rabbitMQDefine;

    @Bean
    public Queue assign_torobotmanager_queue() {
        return new Queue(rabbitMQDefine.getAssign_torobotmanager_queue(), true, false, false);
    }

    @Bean
    public Queue assign_tosmartcall_queue() {
        return new Queue(rabbitMQDefine.getAssign_tosmartcall_queue(), true, false, false);
    }

    @Bean
    public Queue call_tosmartcal_queue() {
        return new Queue(rabbitMQDefine.getCall_tosmartcal_queue(), true, false, false);
    }

    @Bean
    public DirectExchange robotmanager_exchange() {
        return new DirectExchange(rabbitMQDefine.getRobotmanager_exchange(), true, false);
    }

    @Bean
    public DirectExchange smartcall_exchange() {
        return new DirectExchange(rabbitMQDefine.getSmartcall_exchange(), true, false);
    }

    @Bean
    public Binding assign_torobotmanager_queue_binding() {
        return BindingBuilder.bind(assign_torobotmanager_queue()).to(robotmanager_exchange()).with(rabbitMQDefine.getAssign_torobotmanager_queue());
    }

    @Bean
    public Binding assign_tosmartcall_queue_binding() {
        return BindingBuilder.bind(assign_tosmartcall_queue()).to(smartcall_exchange()).with(rabbitMQDefine.getAssign_tosmartcall_queue());
    }

    @Bean
    public Binding call_tosmartcal_queue_binding() {
        return BindingBuilder.bind(call_tosmartcal_queue()).to(smartcall_exchange()).with(rabbitMQDefine.getCall_tosmartcal_queue());
    }

    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }


}
