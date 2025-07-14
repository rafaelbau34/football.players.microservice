package com.football.players.exception;

/**
 * Excepción lanzada cuando no encontramos un recurso.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}