package com.um.prog2.dataservice.service;

import com.um.prog2.dataservice.entity.Inventario;
import com.um.prog2.dataservice.exception.ResourceNotFoundException;
import com.um.prog2.dataservice.repository.InventarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventarioServiceTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioService inventarioService;

    @Test
    void findAll_debeRetornarListaDeInventario() {
        // Arrange
        Inventario inventario = new Inventario();
        inventario.setId(1L);
        inventario.setCantidad(100);
        when(inventarioRepository.findAll()).thenReturn(List.of(inventario));

        // Act
        List<Inventario> resultado = inventarioService.findAll();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(100, resultado.get(0).getCantidad());
        verify(inventarioRepository).findAll();
    }

    @Test
    void findById_cuandoInventarioExiste_debeRetornarInventario() {
        // Arrange
        Inventario inventario = new Inventario();
        inventario.setId(1L);
        when(inventarioRepository.findById(1L)).thenReturn(Optional.of(inventario));

        // Act
        Inventario resultado = inventarioService.findById(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(inventarioRepository).findById(1L);
    }

    @Test
    void findById_cuandoInventarioNoExiste_debeLanzarResourceNotFoundException() {
        // Arrange
        when(inventarioRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            inventarioService.findById(1L);
        });

        verify(inventarioRepository).findById(1L);
    }
}
