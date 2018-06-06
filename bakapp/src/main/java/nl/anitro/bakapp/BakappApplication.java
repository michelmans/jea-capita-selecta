package nl.anitro.bakapp;

import nl.anitro.bakapp.messaging.OrderSender;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BakappApplication {

    public static void main(String[] args) {
        SpringApplication.run(BakappApplication.class, args);
    }

    @Bean
    public OrderSender orderSender(){
        return new OrderSender();
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
