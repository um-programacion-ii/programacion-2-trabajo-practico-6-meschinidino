package com.um.prog2.businessservice.service;

import com.um.prog2.businessservice.client.DataServiceClient;
import com.um.prog2.businessservice.dto.ProductoDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoBusinessServiceTest {

    @Mock
    private DataServiceClient dataServiceClient;

    @InjectMocks
    private ProductoBusinessService productoBusinessService;

    @Test
    void cuandoObtenerTodosLosProductos_entoncesDebeLlamarAlClienteFeign() {
        // Arrange
        // Corregimos el constructor para que coincida con los 6 campos del ProductoDTO
        ProductoDTO productoMock = new ProductoDTO(1L, "Test Product", "Description", BigDecimal.TEN, "Test Category", 10);
        List<ProductoDTO> listaEsperada = Collections.singletonList(productoMock);

        when(dataServiceClient.obtenerTodosLosProductos()).thenReturn(listaEsperada);

        // Act
        List<ProductoDTO> resultado = productoBusinessService.obtenerTodosLosProductos();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Test Product", resultado.get(0).getNombre());

        verify(dataServiceClient, times(1)).obtenerTodosLosProductos();
    }
}

