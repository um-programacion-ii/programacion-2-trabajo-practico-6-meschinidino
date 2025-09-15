package com.um.prog2.businessservice.service;

import com.um.prog2.businessservice.client.DataServiceClient;
import org.springframework.stereotype.Service;

@Service
public class InventarioBusinessService {

    private final DataServiceClient dataServiceClient;

    public InventarioBusinessService(DataServiceClient dataServiceClient) {
        this.dataServiceClient = dataServiceClient;
    }

    // Métodos de lógica de negocio para el inventario irán aquí.
}
