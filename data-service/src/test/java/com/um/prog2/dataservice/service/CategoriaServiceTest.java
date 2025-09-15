package com.um.prog2.dataservice.service;

import com.um.prog2.dataservice.entity.Categoria;
import com.um.prog2.dataservice.exception.ResourceNotFoundException;
import com.um.prog2.dataservice.repository.CategoriaRepository;
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
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    @Test
    void findAll_debeRetornarListaDeCategorias() {
        // Arrange
        Categoria categoria = new Categoria(1L, "Electrónica", "Artículos electrónicos", Collections.emptyList());
        when(categoriaRepository.findAll()).thenReturn(List.of(categoria));

        // Act
        List<Categoria> resultado = categoriaService.findAll();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Electrónica", resultado.get(0).getNombre());
        verify(categoriaRepository).findAll();
    }

    @Test
    void findById_cuandoCategoriaExiste_debeRetornarCategoria() {
        // Arrange
        Categoria categoria = new Categoria(1L, "Electrónica", "Artículos electrónicos", Collections.emptyList());
        // CORRECCIÓN: Mockeamos el REPOSITORIO para que devuelva un Optional
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));

        // Act
        // CORRECCIÓN: El SERVICIO ahora devuelve Categoria directamente
        Categoria resultado = categoriaService.findById(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Electrónica", resultado.getNombre());
        verify(categoriaRepository).findById(1L);
    }

    @Test
    void findById_cuandoCategoriaNoExiste_debeLanzarResourceNotFoundException() {
        // Arrange
        when(categoriaRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        // Verificamos que se lanza la excepción correcta
        assertThrows(ResourceNotFoundException.class, () -> {
            categoriaService.findById(1L);
        });

        verify(categoriaRepository).findById(1L);
    }
}
