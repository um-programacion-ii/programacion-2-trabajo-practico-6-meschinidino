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
    public List<ProductoDTO> obtenerTodosLosProductos() {
        return productoBusinessService.obtenerTodosLosProductos();
    }

    @GetMapping("/productos/{id}")
    public ProductoDTO obtenerProductoPorId(@PathVariable Long id) {
        return productoBusinessService.obtenerProductoPorId(id);
    }

    @PostMapping("/productos")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoDTO crearProducto(@RequestBody ProductoRequest request) {
        return productoBusinessService.crearProducto(request);
    }

    @GetMapping("/productos/categoria/{nombre}")
    public List<ProductoDTO> obtenerProductosPorCategoria(@PathVariable String nombre) {
        return productoBusinessService.obtenerProductosPorCategoria(nombre);
    }

    // Endpoints de Categorías
    @GetMapping("/categorias")
    public List<CategoriaDTO> obtenerTodasLasCategorias() {
        return categoriaBusinessService.obtenerTodasLasCategorias();
    }

    // Endpoints de Reportes
    @GetMapping("/reportes/stock-bajo")
    public List<ProductoDTO> obtenerReporteStockBajo() {
        return productoBusinessService.obtenerProductosConStockBajo();
    }

    @GetMapping("/reportes/valor-inventario")
    public BigDecimal obtenerReporteValorTotalInventario() {
        return productoBusinessService.calcularValorTotalInventario();
    }
}
