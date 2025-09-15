package com.um.prog2.businessservice.service;

import com.um.prog2.businessservice.client.DataServiceClient;
import com.um.prog2.businessservice.dto.InventarioDTO;
import com.um.prog2.businessservice.model.Inventario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventarioBusinessService {
    private final DataServiceClient dataServiceClient;

    public InventarioBusinessService(DataServiceClient dataServiceClient) {
        this.dataServiceClient = dataServiceClient;
    }

    public List<InventarioDTO> obtenerTodoElInventario() {
        return dataServiceClient.obtenerTodoElInventario().stream()
                .map(this::mapearAInventarioDTO)
                .collect(Collectors.toList());
    }

    private InventarioDTO mapearAInventarioDTO(Inventario inventario) {
        if (inventario == null) return null;

        Long productoId = inventario.getProducto() != null ? inventario.getProducto().getId() : null;
        String productoNombre = inventario.getProducto() != null ? inventario.getProducto().getNombre() : "N/A";

        return new InventarioDTO(
                inventario.getId(),
                productoId,
                productoNombre,
                inventario.getCantidad(),
                inventario.getStockMinimo(),
                inventario.getFechaActualizacion()
        );
    }
}

