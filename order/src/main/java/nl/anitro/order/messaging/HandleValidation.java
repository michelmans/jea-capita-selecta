package nl.anitro.order.messaging;

import com.rabbitmq.client.Channel;
import nl.anitro.order.domain.BakappException;
import nl.anitro.order.dto.UserDto;
import nl.anitro.order.service.OrderService;
import nl.anitro.order.util.JsonUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;

import java.io.IOException;

@RabbitListener(queues="VALIDATE_USER_REPLY")
public class HandleValidation {

    @Autowired
    private OrderService orderService;

    @RabbitHandler
    public void recieve(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        UserDto userDto = JsonUtil.decode(message, UserDto.class);

        System.out.println(userDto.isValidated() + " " + userDto.getOrderId());

        try {
            this.orderService.confirmOrder(userDto.isValidated(), userDto.getOrderId());
        } catch (BakappException e) {
            System.out.println(e.getMessage());
        }
        channel.basicAck(tag, true);

    }


}
