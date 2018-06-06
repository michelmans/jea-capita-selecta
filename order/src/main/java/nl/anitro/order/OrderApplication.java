package nl.anitro.order;

import nl.anitro.order.messaging.OrderPlaced;
import nl.anitro.order.messaging.OrderRequest;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrderApplication {
    public static void main(String[] args) {

        SpringApplication.run(OrderApplication.class);

    }

    @Bean
    public OrderRequest orderRequest(){
        return new OrderRequest();
    }

    @Bean
    public OrderPlaced orderPlaced(){
        return new OrderPlaced();
    }

    @Bean
    public Queue placeNewOrderQueue(){
        return new Queue("PLACE_NEW_ORDER");
    }

    @Bean
    public Queue getOrderByName(){
        return new Queue("GET_ORDER_BY_NAME");
    }
}
