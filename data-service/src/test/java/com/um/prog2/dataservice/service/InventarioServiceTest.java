package com.um.prog2.dataservice.service;

import com.um.prog2.dataservice.entity.Inventario;
import com.um.prog2.dataservice.repository.InventarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
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
    void cuandoFindAll_entoncesRetornaListaDeInventarios() {
        // Arrange
        Inventario inventario1 = new Inventario();
        inventario1.setId(1L);
        Inventario inventario2 = new Inventario();
        inventario2.setId(2L);
        List<Inventario> listaEsperada = Arrays.asList(inventario1, inventario2);

        when(inventarioRepository.findAll()).thenReturn(listaEsperada);

        // Act
        List<Inventario> resultado = inventarioService.findAll();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(inventarioRepository, times(1)).findAll();
    }

    @Test
    void cuandoFindByIdExistente_entoncesRetornaInventario() {
        // Arrange
        Inventario inventario = new Inventario();
        inventario.setId(1L);
        inventario.setCantidad(100);

        when(inventarioRepository.findById(1L)).thenReturn(Optional.of(inventario));

        // Act
        Optional<Inventario> resultado = inventarioService.findById(1L);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals(100, resultado.get().getCantidad());
        verify(inventarioRepository, times(1)).findById(1L);
    }

    @Test
    void cuandoFindByIdNoExistente_entoncesRetornaOptionalVacio() {
        // Arrange
        when(inventarioRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        Optional<Inventario> resultado = inventarioService.findById(99L);

        // Assert
        assertFalse(resultado.isPresent());
        verify(inventarioRepository, times(1)).findById(99L);
    }
}
