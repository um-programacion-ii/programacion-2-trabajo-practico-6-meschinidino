package com.um.prog2.businessservice.service;

import com.um.prog2.businessservice.client.DataServiceClient;
import com.um.prog2.businessservice.dto.CategoriaDTO;
import com.um.prog2.businessservice.model.Categoria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriaBusinessServiceTest {

    @Mock
    private DataServiceClient dataServiceClient;

    @InjectMocks
    private CategoriaBusinessService categoriaBusinessService;

    @Test
    void cuandoObtenerTodasLasCategorias_entoncesRetornaListaDeDTOs() {
        // Arrange: Preparamos los 'Models'.
        Categoria categoriaModel = new Categoria(1L, "Electrónica Model", "Descripción");
        when(dataServiceClient.obtenerTodasLasCategorias()).thenReturn(List.of(categoriaModel));

        // Act
        List<CategoriaDTO> resultado = categoriaBusinessService.obtenerTodasLasCategorias();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Electrónica Model", resultado.get(0).getNombre());
    }
}
