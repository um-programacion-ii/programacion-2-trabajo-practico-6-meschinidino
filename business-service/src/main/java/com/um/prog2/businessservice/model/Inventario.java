package com.um.prog2.businessservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Inventario {
    private Long id;

    // Con esta anotación, rompemos el bucle infinito al convertir a JSON.
    @JsonIgnore
    private Producto producto;

    private Integer cantidad;
    private Integer stockMinimo;
    private LocalDateTime fechaActualizacion;
}

