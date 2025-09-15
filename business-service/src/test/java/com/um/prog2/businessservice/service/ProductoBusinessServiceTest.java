package com.um.prog2.businessservice.service;

import com.um.prog2.businessservice.client.DataServiceClient;
import com.um.prog2.businessservice.dto.ProductoDTO;
import com.um.prog2.businessservice.model.Inventario;
import com.um.prog2.businessservice.model.Producto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductoBusinessServiceTest {

    @Mock
    private DataServiceClient dataServiceClient;

    @InjectMocks
    private ProductoBusinessService productoBusinessService;

    @Test
    void cuandoObtenerTodosLosProductos_entoncesRetornaListaDeDTOs() {
        // Arrange: Preparamos los 'Models' que el cliente Feign debe devolver.
        Producto productoModel = new Producto();
        productoModel.setId(1L);
        productoModel.setNombre("Laptop Model");
        productoModel.setPrecio(new BigDecimal("1200.00"));

        Inventario inventarioModel = new Inventario();
        inventarioModel.setCantidad(10);
        productoModel.setInventario(inventarioModel);

        when(dataServiceClient.obtenerTodosLosProductos()).thenReturn(List.of(productoModel));

        // Act: Llamamos al método que queremos probar.
        List<ProductoDTO> resultado = productoBusinessService.obtenerTodosLosProductos();

        // Assert: Verificamos que la "traducción" a DTO fue correcta.
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Laptop Model", resultado.get(0).getNombre());
        assertEquals(10, resultado.get(0).getStock());
    }
}
