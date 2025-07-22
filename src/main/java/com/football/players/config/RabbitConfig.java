package com.football.players.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

  public static final String EXCHANGE = "football.exchange";
  public static final String PLAYER_QUEUE = "player.created.queue";
  public static final String ROUTING_KEY = "player.created";

  @Bean
  Exchange exchange() {
    return ExchangeBuilder.topicExchange(EXCHANGE).durable(true).build();
  }

  @Bean
  Queue playerQueue() {
    return QueueBuilder.durable(PLAYER_QUEUE).build();
  }

  @Bean
  Binding binding(Queue playerQueue, Exchange exchange) {
    return BindingBuilder
            .bind(playerQueue)
            .to(exchange)
            .with(ROUTING_KEY)
            .noargs();
  }
}