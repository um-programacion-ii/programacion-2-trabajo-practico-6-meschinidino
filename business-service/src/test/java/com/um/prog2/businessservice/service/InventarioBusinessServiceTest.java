package com.um.prog2.businessservice.service;

import com.um.prog2.businessservice.client.DataServiceClient;
import com.um.prog2.businessservice.dto.InventarioDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventarioBusinessServiceTest {

    @Mock
    private DataServiceClient dataServiceClient;

    @InjectMocks
    private InventarioBusinessService inventarioBusinessService;

    @Test
    void cuandoObtenerTodoElInventario_entoncesDebeLlamarAlClienteFeign() {
        // Arrange
        // Corregimos el constructor para que coincida con los 6 campos del InventarioDTO
        InventarioDTO inventarioMock = new InventarioDTO(1L, 101L, "Producto Test", 50, 10, LocalDateTime.now());
        List<InventarioDTO> listaEsperada = Collections.singletonList(inventarioMock);

        when(dataServiceClient.obtenerTodoElInventario()).thenReturn(listaEsperada);

        // Act
        List<InventarioDTO> resultado = inventarioBusinessService.obtenerTodoElInventario();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Producto Test", resultado.get(0).getProductoNombre());

        verify(dataServiceClient, times(1)).obtenerTodoElInventario();
    }
}

