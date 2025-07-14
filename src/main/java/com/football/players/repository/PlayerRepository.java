package com.football.players.repository;

import com.football.players.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para Player.
 * Hereda todos los métodos CRUD de JpaRepository.
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
