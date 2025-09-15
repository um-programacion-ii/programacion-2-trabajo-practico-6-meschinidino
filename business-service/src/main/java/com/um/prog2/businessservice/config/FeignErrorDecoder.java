package com.um.prog2.businessservice.config;

import com.um.prog2.businessservice.exception.MicroserviceCommunicationException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());

        if (responseStatus.is5xxServerError()) {
            return new MicroserviceCommunicationException("Error del servidor en el microservicio de datos.");
        } else if (responseStatus.is4xxClientError()) {
            // Podríamos tener lógica más específica para 404, 400, etc.
            return new MicroserviceCommunicationException("Error del cliente al llamar al microservicio de datos.");
        }
        // Para otros errores, usamos el decodificador por defecto.
        return new Default().decode(methodKey, response);
    }
}
