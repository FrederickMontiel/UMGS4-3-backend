package com.miumg.fmontiel.eventos.eventos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AccesDeniedException extends RuntimeException {
    public AccesDeniedException() {
        super("Acceso Denegado");
    }
}
