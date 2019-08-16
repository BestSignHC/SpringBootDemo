package com.hecheng.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitConfig {

    @Bean
    public Queue AQueue() {
        return new Queue("AQueue-For-svc1");
    }

    @Bean
    public Queue BQueue() {
        return new Queue("BQueue-For-svc1");
    }

    @Bean
    public Queue CQueue() {
        return new Queue("CQueue-For-svc1");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeA(Queue AQueue, FanoutExchange exchange) {
        return BindingBuilder.bind(AQueue).to(exchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BQueue, FanoutExchange exchange) {
        return BindingBuilder.bind(BQueue).to(exchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CQueue, FanoutExchange exchange) {
        return BindingBuilder.bind(CQueue).to(exchange);
    }
}
