package com.um.prog2.businessservice.controller;

import com.um.prog2.businessservice.client.DataServiceClient;
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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Con esta anotación, le damos al test una propiedad falsa para que el contexto de Feign pueda cargar.
@TestPropertySource(properties = "data.service.url=http://localhost:9999")
@WebMvcTest(BusinessController.class)
class BusinessControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoBusinessService productoBusinessService;
    @MockBean
    private CategoriaBusinessService categoriaBusinessService;
    @MockBean
    private InventarioBusinessService inventarioBusinessService;

    // El @MockBean del Feign Client sigue siendo necesario para que Spring
    // no intente crear una instancia real durante el test.
    @MockBean
    private DataServiceClient dataServiceClient;


    @Test
    void cuandoPeticionAProductos_entoncesRetornaJsonConListaDeProductos() throws Exception {
        ProductoDTO productoDTO = new ProductoDTO(1L, "Laptop", "Laptop Gamer", new BigDecimal("1500.00"), "Electrónica", 10);
        when(productoBusinessService.obtenerTodosLosProductos()).thenReturn(Collections.singletonList(productoDTO));

        mockMvc.perform(get("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre", is("Laptop")));
    }

    @Test
    void cuandoPeticionACategorias_entoncesRetornaJsonConListaDeCategorias() throws Exception {
        CategoriaDTO categoriaDTO = new CategoriaDTO(1L, "Electrónica", "Artículos de electrónica");
        when(categoriaBusinessService.obtenerTodasLasCategorias()).thenReturn(List.of(categoriaDTO));

        mockMvc.perform(get("/api/categorias")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre", is("Electrónica")));
    }

    @Test
    void cuandoPeticionAInventario_entoncesRetornaJsonConListaDeInventario() throws Exception {
        InventarioDTO inventarioDTO = new InventarioDTO();
        inventarioDTO.setId(1L);
        inventarioDTO.setProductoId(1L);
        inventarioDTO.setProductoNombre("Laptop");
        inventarioDTO.setCantidad(20);

        when(inventarioBusinessService.obtenerTodoElInventario()).thenReturn(List.of(inventarioDTO));

        mockMvc.perform(get("/api/inventario")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].cantidad", is(20)))
                .andExpect(jsonPath("$[0].productoNombre", is("Laptop")));
    }
}

