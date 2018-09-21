package qaautomation.ListenRobot_MQ.Message_Factory;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class Flag_Statistic {
    private List<String> consumerTypeList;
    private Integer aTypeConsumer = 0;
    private Integer bTypeConsumer = 0;
    private Integer cTypeConsumer = 0;
    private Integer dTypeConsumer = 0;
    private Integer eTypeConsumer = 0;
    private Integer unknownTypeConsumer = 0;
    private Integer Sender_CallType_Number = 0;

    public Flag_Statistic() {
        consumerTypeList = new ArrayList<>();
        consumerTypeList.add("A");
        consumerTypeList.add("B");
        consumerTypeList.add("C");
        consumerTypeList.add("D");
        consumerTypeList.add("E");
    }

    @Bean
    Integer Sender_CallType_Count() {
        return Sender_CallType_Number;
    }

    public Integer totalConsumer() {
        return aTypeConsumer + bTypeConsumer + cTypeConsumer + dTypeConsumer + eTypeConsumer + unknownTypeConsumer;
    }


    public void printResult(List<Integer> consumerIdList) {
        System.out.println("Start Call Number ： " + this.Sender_CallType_Number);
        System.out.println("Received Consumer Result : " + this.totalConsumer());
        System.out.println("Missing Consumer Result : " + (this.Sender_CallType_Number - this.totalConsumer()));
        System.out.println("A TypeConsumer : " + this.getATypeConsumer());
        System.out.println("B TypeConsumer : " + this.getBTypeConsumer());
        System.out.println("C TypeConsumer : " + this.getCTypeConsumer());
        System.out.println("D TypeConsumer : " + this.getDTypeConsumer());
        System.out.println("E TypeConsumer : " + this.getETypeConsumer());
        System.out.println("Unknown TypeConsumer : " + this.getUnknownTypeConsumer());
    }
}
