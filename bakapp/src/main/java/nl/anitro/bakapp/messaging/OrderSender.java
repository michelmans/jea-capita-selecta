package nl.anitro.bakapp.messaging;

import nl.anitro.bakapp.domain.Product;
import nl.anitro.bakapp.domain.User;
import nl.anitro.bakapp.dto.OrderDto;
import nl.anitro.bakapp.dto.ProductDto;
import nl.anitro.bakapp.util.JsonUtil;
import nl.anitro.bakapp.wrapper.ProductWrapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;

import java.util.List;

public class OrderSender {

    @Autowired
    private RabbitTemplate rabbitQueue;

    @RabbitHandler
    public void placeOrder(String username, ProductWrapper products){

        OrderDto orderDto = new OrderDto();
        orderDto.setUsername(username);
        orderDto.setProducts(products.getProducts());

        rabbitQueue.convertAndSend("PLACE_NEW_ORDER", JsonUtil.encode(orderDto));

    }

    @RabbitHandler
    public void getOrder(User user){
        Object o = rabbitQueue.convertSendAndReceive("GET_ORDER_BY_NAME", JsonUtil.encode(user));
    }

}
