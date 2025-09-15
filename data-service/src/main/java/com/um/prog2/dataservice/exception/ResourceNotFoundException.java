package com.um.prog2.dataservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Esta anotación le dice a Spring que cuando esta excepción sea lanzada,
// la respuesta HTTP debe ser un 404 (Not Found).
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
