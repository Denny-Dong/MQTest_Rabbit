package qaautomation.ListenRobot_MQ.RabbitMQ_Manage;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.basic;

public class RabbitMQ_BaseConfig {
    public RabbitMQ_BaseConfig() {
        RestAssured.baseURI = "http://192.168.20.216";
        RestAssured.port = 15672;
        RestAssured.authentication = basic("mqadmin", "mqadminpassword");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
