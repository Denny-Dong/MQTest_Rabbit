package qaautomation.ListenRobot_MQ.Message_Factory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Gateway_Factory {

    protected String id;
    protected String number;
    protected String sip_server;
    protected String sip_user_name;
    protected String sip_password;
    protected String sip_register;
    protected String capacity;
    protected String router;
    protected String callee;

    protected Gateway_Factory getGatewayConfiguration() {
        Gateway_Factory gatewayFactory = new Gateway_Factory();
        gatewayFactory.setId(this.id);
        gatewayFactory.setNumber(this.number);
        gatewayFactory.setSip_server(this.sip_server);
        gatewayFactory.setSip_user_name(this.sip_user_name);
        gatewayFactory.setSip_password(this.sip_password);
        gatewayFactory.setSip_register(this.sip_register);
        gatewayFactory.setCapacity(this.capacity);
        gatewayFactory.setRouter(this.router);
        gatewayFactory.setCallee(this.callee);
        return gatewayFactory;
    }

}
