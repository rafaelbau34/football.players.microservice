package com.football.players.controller;

import com.football.players.dto.PlayerDto;
import com.football.players.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * API REST para gestionar jugadores.
 * Se valida automáticamente el @RequestBody gracias a @Valid.
 * @Validated habilita validación en @PathVariable o @RequestParam si se necesitara.
 */
@RestController
@RequestMapping("/api/players")
@Validated
public class PlayerController {

    private final PlayerService svc;

    public PlayerController(PlayerService svc) {
        this.svc = svc;
    }

    /**
     * Lista todos los jugadores.
     * GET /api/players
     */
    @GetMapping
    public List<PlayerDto> getAll() {
        return svc.findAll();
    }

    /**
     * Crea un jugador nuevo.
     * POST /api/players
     * @param dto PlayerDto con campos validados (@NotBlank, @Size, etc.)
     * @return El PlayerDto creado con su ID asignado
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerDto create(@Valid @RequestBody PlayerDto dto) {
        return svc.create(dto);
    }

    /**
     * Obtiene un jugador por su ID.
     * GET /api/players/{id}
     * @param id Identificador del jugador
     * @return PlayerDto con los datos del jugador
     */
    @GetMapping("/{id}")
    public PlayerDto getOne(@PathVariable Long id) {
        return svc.findById(id);
    }

    /**
     * Actualiza un jugador existente.
     * PUT /api/players/{id}
     * @param id  Identificador del jugador a actualizar
     * @param dto PlayerDto con los nuevos datos (campos validados)
     * @return PlayerDto con los datos actualizados
     */
    @PutMapping("/{id}")
    public PlayerDto update(
            @PathVariable Long id,
            @Valid @RequestBody PlayerDto dto
    ) {
        return svc.update(id, dto);
    }

    /**
     * Elimina un jugador por su ID.
     * DELETE /api/players/{id}
     * @param id Identificador del jugador a eliminar
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        svc.delete(id);
    }
}