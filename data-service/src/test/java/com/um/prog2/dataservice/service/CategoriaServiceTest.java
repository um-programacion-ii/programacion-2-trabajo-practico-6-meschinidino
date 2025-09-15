package com.um.prog2.dataservice.service;

import com.um.prog2.dataservice.entity.Categoria;
import com.um.prog2.dataservice.repository.CategoriaRepository;
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
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    @Test
    void cuandoFindAll_entoncesRetornaListaDeCategorias() {
        // Arrange
        Categoria categoria1 = new Categoria();
        categoria1.setId(1L);
        Categoria categoria2 = new Categoria();
        categoria2.setId(2L);
        List<Categoria> listaEsperada = Arrays.asList(categoria1, categoria2);

        when(categoriaRepository.findAll()).thenReturn(listaEsperada);

        // Act
        List<Categoria> resultado = categoriaService.findAll();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(categoriaRepository, times(1)).findAll();
    }

    @Test
    void cuandoFindByIdExistente_entoncesRetornaCategoria() {
        // Arrange
        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNombre("Electrónica");

        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));

        // Act
        Optional<Categoria> resultado = categoriaService.findById(1L);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals("Electrónica", resultado.get().getNombre());
        verify(categoriaRepository, times(1)).findById(1L);
    }

    @Test
    void cuandoFindByIdNoExistente_entoncesRetornaOptionalVacio() {
        // Arrange
        when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        Optional<Categoria> resultado = categoriaService.findById(99L);

        // Assert
        assertFalse(resultado.isPresent());
        verify(categoriaRepository, times(1)).findById(99L);
    }
}
