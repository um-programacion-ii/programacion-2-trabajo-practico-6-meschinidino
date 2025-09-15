package com.um.prog2.businessservice.service;

import com.um.prog2.businessservice.client.DataServiceClient;
import com.um.prog2.businessservice.dto.CategoriaDTO;
import com.um.prog2.businessservice.model.Categoria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaBusinessService {
    private final DataServiceClient dataServiceClient;

    public CategoriaBusinessService(DataServiceClient dataServiceClient) {
        this.dataServiceClient = dataServiceClient;
    }

    public List<CategoriaDTO> obtenerTodasLasCategorias() {
        return dataServiceClient.obtenerTodasLasCategorias().stream()
                .map(this::mapearACategoriaDTO)
                .collect(Collectors.toList());
    }

    private CategoriaDTO mapearACategoriaDTO(Categoria categoria) {
        return new CategoriaDTO(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion()
        );
    }
}

