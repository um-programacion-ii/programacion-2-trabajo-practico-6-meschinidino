package com.um.prog2.businessservice.client;

import com.um.prog2.businessservice.dto.ProductoRequest;
import com.um.prog2.businessservice.model.Categoria;
import com.um.prog2.businessservice.model.Inventario;
import com.um.prog2.businessservice.model.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

// Este es el "contrato" o "control remoto" para hablar con el data-service.
@FeignClient(name = "data-service", url = "${data.service.url}")
public interface DataServiceClient {

    // Endpoints de Producto
    @GetMapping("/data/productos")
    List<Producto> obtenerTodosLosProductos();

    @GetMapping("/data/productos/{id}")
    Producto obtenerProductoPorId(@PathVariable Long id);

    @PostMapping("/data/productos")
    Producto crearProducto(@RequestBody ProductoRequest request);

    @GetMapping("/data/productos/categoria/{nombre}")
    List<Producto> obtenerProductosPorCategoria(@PathVariable String nombre);

    // Endpoints de Categoria
    @GetMapping("/data/categorias")
    List<Categoria> obtenerTodasLasCategorias();

    @GetMapping("/data/categorias/nombre/{nombre}")
    Categoria obtenerCategoriaPorNombre(@PathVariable String nombre);

    // Endpoints de Inventario
    @GetMapping("/data/inventario")
    List<Inventario> obtenerTodoElInventario();

    @GetMapping("/data/inventario/stock-bajo")
    List<Inventario> obtenerProductosConStockBajo();
}

