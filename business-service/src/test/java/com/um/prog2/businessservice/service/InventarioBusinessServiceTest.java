package com.um.prog2.businessservice.service;

import com.um.prog2.businessservice.client.DataServiceClient;
import com.um.prog2.businessservice.dto.InventarioDTO;
import com.um.prog2.businessservice.model.Inventario;
import com.um.prog2.businessservice.model.Producto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventarioBusinessServiceTest {

    @Mock
    private DataServiceClient dataServiceClient;

    @InjectMocks
    private InventarioBusinessService inventarioBusinessService;

    @Test
    void cuandoObtenerTodoElInventario_entoncesRetornaListaDeDTOs() {
        // Arrange: Preparamos los 'Models'.
        Producto productoModel = new Producto();
        productoModel.setId(1L);
        productoModel.setNombre("Laptop Model");

        Inventario inventarioModel = new Inventario();
        inventarioModel.setId(1L);
        inventarioModel.setCantidad(25);
        inventarioModel.setProducto(productoModel);

        when(dataServiceClient.obtenerTodoElInventario()).thenReturn(List.of(inventarioModel));

        // Act
        List<InventarioDTO> resultado = inventarioBusinessService.obtenerTodoElInventario();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(25, resultado.get(0).getCantidad());
        assertEquals("Laptop Model", resultado.get(0).getProductoNombre());
    }
}
