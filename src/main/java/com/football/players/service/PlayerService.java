package com.football.players.service;

import com.football.players.dto.PlayerDto;
import java.util.List;

/**
 * Contrato de la lógica de negocio para Players.
 */
public interface PlayerService {
    List<PlayerDto> findAll();
    PlayerDto findById(Long id);
    PlayerDto create(PlayerDto dto);
    PlayerDto update(Long id, PlayerDto dto);
    void delete(Long id);
}