package com.um.prog2.businessservice.service;

import com.um.prog2.businessservice.client.DataServiceClient;
import com.um.prog2.businessservice.dto.ProductoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoBusinessService {

    private final DataServiceClient dataServiceClient;

    public ProductoBusinessService(DataServiceClient dataServiceClient) {
        this.dataServiceClient = dataServiceClient;
    }

    /**
     * Llama al microservicio de datos para obtener todos los productos.
     * @return Una lista de DTOs de productos.
     */
    public List<ProductoDTO> obtenerTodosLosProductos() {
        // Aquí usamos nuestro cliente Feign para comunicarnos con data-service
        return dataServiceClient.obtenerTodosLosProductos();
    }
}

