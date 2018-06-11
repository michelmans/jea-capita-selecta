package nl.anitro.bakapp.messaging;

import com.rabbitmq.client.Channel;
import nl.anitro.bakapp.domain.BakappException;
import nl.anitro.bakapp.domain.User;
import nl.anitro.bakapp.dto.UserDto;
import nl.anitro.bakapp.service.UserService;
import nl.anitro.bakapp.util.JsonUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;

import java.io.IOException;

@RabbitListener(queues="VALIDATE_USER")
public class ValidateUserRequest {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidateUserReply validateUserReply;

    @RabbitHandler
    public void receiveMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        UserDto userDto = JsonUtil.decode(message, UserDto.class);
        try {
            User u = userService.getUserByName(userDto.getUsername());
            if(userDto.getUsername().equalsIgnoreCase(u.getUsername())) {
                userDto.setValidated(true);
                validateUserReply.replyRequest(userDto);
            } else {
                userDto.setValidated(false);
                validateUserReply.replyRequest(userDto);
            }
        } catch (BakappException e) {
            userDto.setValidated(false);
            validateUserReply.replyRequest(userDto);
        }
        channel.basicAck(tag, true);
    }

}
