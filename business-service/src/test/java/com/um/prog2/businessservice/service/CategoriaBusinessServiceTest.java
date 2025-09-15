package com.um.prog2.businessservice.service;

import com.um.prog2.businessservice.client.DataServiceClient;
import com.um.prog2.businessservice.dto.CategoriaDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaBusinessServiceTest {

    @Mock
    private DataServiceClient dataServiceClient;

    @InjectMocks
    private CategoriaBusinessService categoriaBusinessService;

    @Test
    void cuandoObtenerTodasLasCategorias_entoncesDebeLlamarAlClienteFeign() {
        // Arrange
        CategoriaDTO categoriaMock = new CategoriaDTO(1L, "Test Category", "Description");
        List<CategoriaDTO> listaEsperada = Collections.singletonList(categoriaMock);

        when(dataServiceClient.obtenerTodasLasCategorias()).thenReturn(listaEsperada);

        // Act
        List<CategoriaDTO> resultado = categoriaBusinessService.obtenerTodasLasCategorias();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Test Category", resultado.get(0).getNombre());
        verify(dataServiceClient, times(1)).obtenerTodasLasCategorias();
    }
}
