package com.um.prog2.businessservice.service;

import com.um.prog2.businessservice.client.DataServiceClient;
import org.springframework.stereotype.Service;

@Service
public class CategoriaBusinessService {

    private final DataServiceClient dataServiceClient;

    public CategoriaBusinessService(DataServiceClient dataServiceClient) {
        this.dataServiceClient = dataServiceClient;
    }

    // Métodos de lógica de negocio para categorías irán aquí.
}
