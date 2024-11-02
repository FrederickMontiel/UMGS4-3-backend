package com.miumg.fmontiel.eventos.eventos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {
    public ConflictException() {
        super("Se han encontrado registros con conflictos");
    }

    public ConflictException(String mensaje) {
        super(mensaje);
    }
}
