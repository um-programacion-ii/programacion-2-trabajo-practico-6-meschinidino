package com.um.prog2.businessservice.service;

import com.um.prog2.businessservice.client.DataServiceClient;
import com.um.prog2.businessservice.dto.CategoriaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaBusinessService {
    private final DataServiceClient dataServiceClient;

    public CategoriaBusinessService(DataServiceClient dataServiceClient) {
        this.dataServiceClient = dataServiceClient;
    }

    /**
     * Llama al microservicio de datos para obtener todas las categorías.
     * @return Una lista de DTOs de categorías.
     */
    public List<CategoriaDTO> obtenerTodasLasCategorias() {
        return dataServiceClient.obtenerTodasLasCategorias();
    }
}

