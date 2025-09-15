package com.um.prog2.businessservice.controller;

import com.um.prog2.businessservice.dto.CategoriaDTO;
import com.um.prog2.businessservice.dto.InventarioDTO;
import com.um.prog2.businessservice.dto.ProductoDTO;
import com.um.prog2.businessservice.service.CategoriaBusinessService;
import com.um.prog2.businessservice.service.InventarioBusinessService;
import com.um.prog2.businessservice.service.ProductoBusinessService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;

// @WebMvcTest es ideal para probar controladores. Carga solo la capa web, no el contexto completo.
@WebMvcTest(BusinessController.class)
class BusinessControllerTest {

    @Autowired
    private MockMvc mockMvc; // Permite simular llamadas HTTP a nuestros endpoints

    // @MockBean crea un "mock" o simulación de estos servicios.
    // El controlador los usará, pero nosotros controlamos qué devuelven en cada test.
    @MockBean
    private ProductoBusinessService productoBusinessService;

    @MockBean
    private CategoriaBusinessService categoriaBusinessService;

    @MockBean
    private InventarioBusinessService inventarioBusinessService;

    @Test
    void cuandoPeticionAProductos_entoncesRetornaJsonConListaDeProductos() throws Exception {
        // Arrange: Preparamos un DTO de producto y le decimos al servicio mockeado que devuelva eso.
        ProductoDTO productoDTO = new ProductoDTO(1L, "Laptop", "Laptop Gamer", new BigDecimal("1500.00"), "Electrónica", 10);
        when(productoBusinessService.obtenerTodosLosProductos()).thenReturn(Collections.singletonList(productoDTO));

        // Act & Assert: Hacemos la llamada a /api/productos y verificamos la respuesta.
        mockMvc.perform(get("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Esperamos un 200 OK
                .andExpect(jsonPath("$", hasSize(1))) // Esperamos un array JSON de tamaño 1
                .andExpect(jsonPath("$[0].nombre", is("Laptop"))); // Verificamos el nombre del producto
    }

    @Test
    void cuandoPeticionACategorias_entoncesRetornaJsonConListaDeCategorias() throws Exception {
        // Arrange
        CategoriaDTO categoriaDTO = new CategoriaDTO(1L, "Electrónica", "Artículos de electrónica");
        when(categoriaBusinessService.obtenerTodasLasCategorias()).thenReturn(List.of(categoriaDTO));

        // Act & Assert
        mockMvc.perform(get("/api/categorias")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre", is("Electrónica")));
    }

    @Test
    void cuandoPeticionAInventario_entoncesRetornaJsonConListaDeInventario() throws Exception {
        // Arrange
        // CORREGIDO: Usamos un constructor vacío y setters para evitar el error.
        InventarioDTO inventarioDTO = new InventarioDTO();
        inventarioDTO.setId(1L);
        inventarioDTO.setProductoId(1L);
        inventarioDTO.setProductoNombre("Laptop");
        inventarioDTO.setCantidad(20);

        when(inventarioBusinessService.obtenerTodoElInventario()).thenReturn(List.of(inventarioDTO));

        // Act & Assert
        mockMvc.perform(get("/api/inventario")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].cantidad", is(20)))
                .andExpect(jsonPath("$[0].productoNombre", is("Laptop")));
    }
}

