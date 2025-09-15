package com.um.prog2.businessservice.controller;

import com.um.prog2.businessservice.dto.CategoriaDTO;
import com.um.prog2.businessservice.dto.InventarioDTO;
import com.um.prog2.businessservice.dto.ProductoDTO;
import com.um.prog2.businessservice.dto.ProductoRequest;
import com.um.prog2.businessservice.service.CategoriaBusinessService;
import com.um.prog2.businessservice.service.InventarioBusinessService;
import com.um.prog2.businessservice.service.ProductoBusinessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BusinessController {

    private final ProductoBusinessService productoBusinessService;
    private final CategoriaBusinessService categoriaBusinessService;
    private final InventarioBusinessService inventarioBusinessService;

    public BusinessController(ProductoBusinessService productoBusinessService, CategoriaBusinessService categoriaBusinessService, InventarioBusinessService inventarioBusinessService) {
        this.productoBusinessService = productoBusinessService;
        this.categoriaBusinessService = categoriaBusinessService;
        this.inventarioBusinessService = inventarioBusinessService;
    }

    // Endpoints de Productos
    @GetMapping("/productos")
    public ResponseEntity<List<ProductoDTO>> obtenerTodosLosProductos() {
        return ResponseEntity.ok(productoBusinessService.obtenerTodosLosProductos());
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoBusinessService.obtenerProductoPorId(id));
    }

    @PostMapping("/productos")
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoRequest request) {
        return new ResponseEntity<>(productoBusinessService.crearProducto(request), HttpStatus.CREATED);
    }

    @GetMapping("/productos/categoria/{nombre}")
    public ResponseEntity<List<ProductoDTO>> obtenerProductosPorCategoria(@PathVariable String nombre) {
        return ResponseEntity.ok(productoBusinessService.obtenerProductosPorCategoria(nombre));
    }

    // Endpoint de Categorías
    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaDTO>> obtenerTodasLasCategorias() {
        return ResponseEntity.ok(categoriaBusinessService.obtenerTodasLasCategorias());
    }

    // Endpoint de Inventario (¡EL QUE FALTABA!)
    @GetMapping("/inventario")
    public ResponseEntity<List<InventarioDTO>> obtenerTodoElInventario() {
        return ResponseEntity.ok(inventarioBusinessService.obtenerTodoElInventario());
    }

    // Endpoints de Reportes
    @GetMapping("/reportes/stock-bajo")
    public ResponseEntity<List<ProductoDTO>> obtenerProductosConStockBajo() {
        return ResponseEntity.ok(productoBusinessService.obtenerProductosConStockBajo());
    }

    @GetMapping("/reportes/valor-inventario")
    public ResponseEntity<BigDecimal> obtenerValorTotalInventario() {
        return ResponseEntity.ok(productoBusinessService.calcularValorTotalInventario());
    }
}

