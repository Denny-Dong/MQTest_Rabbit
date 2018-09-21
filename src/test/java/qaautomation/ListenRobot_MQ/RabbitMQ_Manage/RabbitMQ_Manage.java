package qaautomation.ListenRobot_MQ.RabbitMQ_Manage;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RabbitMQ_Manage extends RabbitMQ_BaseConfig {
    @Test
    public void cleanQueues() {
        List<Map<String, Object>> queueList =
                given().when().get("/api/queues").then().statusCode(200).extract().body().as(ArrayList.class);
        for (Map<String, Object> queueInfo : queueList
        ) {
            given().pathParam("vhost", queueInfo.get("vhost"))
                    .pathParam("name", queueInfo.get("name"))
                    .when().delete("/api/queues/{vhost}/{name}")
                    .then().assertThat().statusCode(204);
        }
    }


    @Test
    public void cleanExchange() {
        List<Map<String, Object>> exchangeList = given()
                .when().get("/api/exchanges")
                .then().statusCode(200).extract().body().as(ArrayList.class);
        for (Map<String, Object> exchangeInfo :
                exchangeList) {
            String exchangeName = (String) exchangeInfo.get("name");
            if (!exchangeName.equals("") && exchangeName.indexOf("amq") == -1) {
                given().pathParam("vhost", exchangeInfo.get("vhost"))
                        .pathParam("name", exchangeInfo.get("name"))
                        .when().delete("/api/exchanges/{vhost}/{name}")
                        .then().assertThat().statusCode(204);
            } else {
                System.out.println("Rabbit MQ default exchange " + exchangeName + " needless to be removed !");
            }

        }
    }

}
