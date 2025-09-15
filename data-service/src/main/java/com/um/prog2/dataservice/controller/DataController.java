package com.um.prog2.dataservice.controller;

import com.um.prog2.dataservice.entity.Categoria;
import com.um.prog2.dataservice.entity.Inventario;
import com.um.prog2.dataservice.entity.Producto;
import com.um.prog2.dataservice.service.CategoriaService;
import com.um.prog2.dataservice.service.InventarioService;
import com.um.prog2.dataservice.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> obtenerTodasLasCategorias() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @GetMapping("/inventario")
    public ResponseEntity<List<Inventario>> obtenerTodoElInventario() {
        return ResponseEntity.ok(inventarioService.findAll());
    }
}
