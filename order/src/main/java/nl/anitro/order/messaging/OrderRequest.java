package nl.anitro.order.messaging;

import nl.anitro.order.dto.OrderDto;
import nl.anitro.order.util.JsonUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "GET_ORDER_BY_NAME")
public class OrderRequest {

    @RabbitHandler
    public void receive(String message){
        System.out.println("MQMESSAGE: " + message);
        OrderDto order = JsonUtil.decode(message, OrderDto.class);

    }

}
