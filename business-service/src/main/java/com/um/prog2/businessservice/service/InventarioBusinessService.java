package com.um.prog2.businessservice.service;

import com.um.prog2.businessservice.client.DataServiceClient;
import com.um.prog2.businessservice.dto.InventarioDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioBusinessService {
    private final DataServiceClient dataServiceClient;

    public InventarioBusinessService(DataServiceClient dataServiceClient) {
        this.dataServiceClient = dataServiceClient;
    }

    /**
     * Llama al microservicio de datos para obtener todo el inventario.
     * @return Una lista de DTOs de inventario.
     */
    public List<InventarioDTO> obtenerTodoElInventario() {
        return dataServiceClient.obtenerTodoElInventario();
    }
}

