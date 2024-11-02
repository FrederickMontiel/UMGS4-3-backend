package com.miumg.fmontiel.eventos.eventos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // Configura el 404 en la excepción
public class NotFoundedException extends RuntimeException {
    public NotFoundedException() {
        super("No se ha encontrado el recurso solicitado");
    }
}
