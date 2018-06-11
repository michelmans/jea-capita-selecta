package nl.anitro.bakapp;

import nl.anitro.bakapp.messaging.ValidateUserReply;
import nl.anitro.bakapp.messaging.ValidateUserRequest;
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
    public ValidateUserRequest validateUserRequest(){
        return new ValidateUserRequest();
    }

    @Bean
    public ValidateUserReply validateUserReply() { return new ValidateUserReply(); }

    @Bean
    public Queue validateUserReplyQueue(){
        return new Queue("VALIDATE_USER_REPLY");
    }

    @Bean
    public Queue validateUserQueue(){
        return new Queue("VALIDATE_USER");
    }


}
