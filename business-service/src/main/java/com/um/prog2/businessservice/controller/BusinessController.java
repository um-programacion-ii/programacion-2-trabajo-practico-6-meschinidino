package com.um.prog2.businessservice.controller;

import com.um.prog2.businessservice.dto.CategoriaDTO;
import com.um.prog2.businessservice.dto.InventarioDTO;
import com.um.prog2.businessservice.dto.ProductoDTO;
import com.um.prog2.businessservice.service.CategoriaBusinessService;
import com.um.prog2.businessservice.service.InventarioBusinessService;
import com.um.prog2.businessservice.service.ProductoBusinessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api") // Usamos /api para diferenciarlo del /data del otro microservicio
public class BusinessController {

    private final ProductoBusinessService productoBusinessService;
    private final CategoriaBusinessService categoriaBusinessService;
    private final InventarioBusinessService inventarioBusinessService;

    public BusinessController(ProductoBusinessService productoBusinessService, CategoriaBusinessService categoriaBusinessService, InventarioBusinessService inventarioBusinessService) {
        this.productoBusinessService = productoBusinessService;
        this.categoriaBusinessService = categoriaBusinessService;
        this.inventarioBusinessService = inventarioBusinessService;
    }

    @GetMapping("/productos")
    public ResponseEntity<List<ProductoDTO>> obtenerTodosLosProductos() {
        return ResponseEntity.ok(productoBusinessService.obtenerTodosLosProductos());
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaDTO>> obtenerTodasLasCategorias() {
        return ResponseEntity.ok(categoriaBusinessService.obtenerTodasLasCategorias());
    }

    @GetMapping("/inventario")
    public ResponseEntity<List<InventarioDTO>> obtenerTodoElInventario() {
        return ResponseEntity.ok(inventarioBusinessService.obtenerTodoElInventario());
    }
}
