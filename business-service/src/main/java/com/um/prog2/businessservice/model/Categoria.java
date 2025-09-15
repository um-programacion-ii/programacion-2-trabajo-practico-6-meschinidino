package com.um.prog2.businessservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
    private Long id;
    private String nombre;
    private String descripcion;

    // Con esta anotación, rompemos el segundo y último bucle infinito.
    @JsonIgnore
    private List<Producto> productos;

    // Constructor adicional para facilitar la creación en los tests
    public Categoria(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}

