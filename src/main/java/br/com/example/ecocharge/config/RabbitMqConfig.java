package br.com.example.ecocharge.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String QUEUE = "ecocharge-queue";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE, true);
    }
}
