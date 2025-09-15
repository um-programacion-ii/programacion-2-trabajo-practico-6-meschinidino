package com.um.prog2.businessservice.client;

import com.um.prog2.businessservice.dto.CategoriaDTO;
import com.um.prog2.businessservice.dto.InventarioDTO;
import com.um.prog2.businessservice.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "data-service", url = "${data.service.url:http://localhost:8081}")
public interface DataServiceClient {

    @GetMapping("/data/productos")
    List<ProductoDTO> obtenerTodosLosProductos();

    // Agregamos el método que faltaba para las categorías
    @GetMapping("/data/categorias")
    List<CategoriaDTO> obtenerTodasLasCategorias();

    // Y también el de inventario para que no falle el otro test
    @GetMapping("/data/inventario")
    List<InventarioDTO> obtenerTodoElInventario();
}

