package nl.anitro.order.messaging;

import nl.anitro.order.domain.Order;
import nl.anitro.order.dto.UserDto;
import nl.anitro.order.util.JsonUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidateUser {

    @Autowired
    private RabbitTemplate rabbitQueue;

    @RabbitHandler
    public void validateUser(String username, Long orderId){
        UserDto user = new UserDto();
        user.setUsername(username);
        user.setValidated(false);
        user.setOrderId(orderId);

        rabbitQueue.convertAndSend("VALIDATE_USER", JsonUtil.encode(user));

    }

}
