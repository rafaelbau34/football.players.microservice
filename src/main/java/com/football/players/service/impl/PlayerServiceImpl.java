package com.football.players.service.impl;

import com.football.players.dto.PlayerDto;
import com.football.players.entity.Player;
import com.football.players.exception.ResourceNotFoundException;
import com.football.players.repository.PlayerRepository;
import com.football.players.service.PlayerService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de PlayerService.
 * Mapea entidad ↔ DTO y maneja las excepciones.
 */
@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository repo;

    public PlayerServiceImpl(PlayerRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<PlayerDto> findAll() {
        return repo.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDto findById(Long id) {
        Player p = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player con id " + id + " no encontrado"));
        return toDto(p);
    }

    @Override
    public PlayerDto create(PlayerDto dto) {
        Player p = new Player();
        p.setName(dto.name());
        p.setPosition(dto.position());
        return toDto(repo.save(p));
    }

    @Override
    public PlayerDto update(Long id, PlayerDto dto) {
        Player p = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player con id " + id + " no encontrado"));
        p.setName(dto.name());
        p.setPosition(dto.position());
        return toDto(repo.save(p));
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Player con id " + id + " no encontrado");
        }
        repo.deleteById(id);
    }

    private PlayerDto toDto(Player p) {
        return new PlayerDto(p.getId(), p.getName(), p.getPosition());
    }
}
