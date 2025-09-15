package com.um.prog2.dataservice.controller;

import com.um.prog2.dataservice.entity.Categoria;
import com.um.prog2.dataservice.entity.Inventario;
import com.um.prog2.dataservice.entity.Producto;
import com.um.prog2.dataservice.service.CategoriaService;
import com.um.prog2.dataservice.service.InventarioService;
import com.um.prog2.dataservice.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;
    private final InventarioService inventarioService;

    public DataController(ProductoService productoService, CategoriaService categoriaService, InventarioService inventarioService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
        this.inventarioService = inventarioService;
    }

    // Endpoints de Productos
    @GetMapping("/productos")
    public List<Producto> obtenerTodosLosProductos() {
        return productoService.findAll();
    }

    @GetMapping("/productos/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id) {
        return productoService.findById(id);
    }

    @PostMapping("/productos")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.save(producto);
    }

    @PutMapping("/productos/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.update(id, producto);
    }

    @DeleteMapping("/productos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarProducto(@PathVariable Long id) {
        productoService.deleteById(id);
    }

    @GetMapping("/productos/categoria/{nombre}")
    public List<Producto> obtenerProductosPorCategoria(@PathVariable String nombre) {
        return productoService.findByCategoriaNombre(nombre);
    }

    // Endpoints de Categorías
    @GetMapping("/categorias")
    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaService.findAll();
    }

    // Endpoints de Inventario
    @GetMapping("/inventario")
    public List<Inventario> obtenerTodoElInventario() {
        return inventarioService.findAll();
    }

    @GetMapping("/inventario/stock-bajo")
    public List<Inventario> obtenerProductosConStockBajo() {
        return inventarioService.findProductosConStockBajo();
    }
}
