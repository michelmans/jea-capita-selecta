package nl.anitro.order.messaging;

import nl.anitro.order.domain.Order;
import nl.anitro.order.domain.OrderProduct;
import nl.anitro.order.dto.OrderDto;
import nl.anitro.order.service.OrderService;
import nl.anitro.order.util.JsonUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@RabbitListener(queues = "PLACE_NEW_ORDER")
public class OrderPlaced {

    @Autowired
    private OrderService orderService;

    @RabbitHandler
    public void receive(String message){

        List<OrderProduct> orderProducts = new ArrayList<>();

        System.out.println("MQMESSAGE: " + message);
        OrderDto order = JsonUtil.decode(message, OrderDto.class);

        Order orderD = new Order();
        orderD.setUsername(order.getUsername());
        order.getProducts().forEach(item -> {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProductname(item.getProductname());
            orderProducts.add(orderProduct);
        });


        //orderService.saveOrder(orderD);
        System.out.println("kek");

    }

}
