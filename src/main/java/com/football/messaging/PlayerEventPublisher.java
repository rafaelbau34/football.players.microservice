package com.football.messaging;

import com.football.players.config.RabbitConfig;
import com.football.players.dto.PlayerDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * Componente responsable de publicar eventos de jugador creado en RabbitMQ.
 */
@Component
public class PlayerEventPublisher {

  private final RabbitTemplate rabbitTemplate;

  public PlayerEventPublisher(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  /**
   * Envía el PlayerDto al exchange configurado, usando la routing key.
   */
  public void publishPlayerCreated(PlayerDto dto) {
    rabbitTemplate.convertAndSend(
      RabbitConfig.EXCHANGE,
      RabbitConfig.PLAYER_QUEUE,
      dto
    );
  }
}