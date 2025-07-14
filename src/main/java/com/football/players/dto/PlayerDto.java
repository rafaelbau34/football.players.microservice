package com.football.players.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para exponer sólo los campos necesarios de Player,
 * con anotaciones de validación para que @Valid funcione.
 */
public record PlayerDto(
        Long id,

        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
        String name,

        @NotBlank(message = "La posición no puede estar vacía")
        @Size(max = 50, message = "La posición no puede exceder 50 caracteres")
        String position
) { }
