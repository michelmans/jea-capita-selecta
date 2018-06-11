package nl.anitro.bakapp.messaging;

import nl.anitro.bakapp.dto.UserDto;
import nl.anitro.bakapp.util.JsonUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidateUserReply {

    @Autowired
    private RabbitTemplate rabbitQueue;

    @RabbitHandler
    public void replyRequest(UserDto userDto){
        System.out.println(userDto.isValidated());
        rabbitQueue.convertAndSend("VALIDATE_USER_REPLY", JsonUtil.encode(userDto));
    }

}
