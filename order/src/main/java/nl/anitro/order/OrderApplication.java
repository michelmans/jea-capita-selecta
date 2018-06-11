package nl.anitro.order;

import nl.anitro.order.messaging.HandleValidation;
import nl.anitro.order.messaging.ValidateUser;
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
    public ValidateUser validateUser(){
        return new ValidateUser();
    }

    @Bean
    public HandleValidation handleValidation() { return new HandleValidation(); }


    @Bean
    public Queue validateUserReplyQueue(){
        return new Queue("VALIDATE_USER_REPLY");
    }

    @Bean
    public Queue validateUserQueue(){
        return new Queue("VALIDATE_USER");
    }

}
