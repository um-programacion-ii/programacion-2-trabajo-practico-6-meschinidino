package com.um.prog2.businessservice.service;

import com.um.prog2.businessservice.client.DataServiceClient;
import com.um.prog2.businessservice.dto.ProductoDTO;
import com.um.prog2.businessservice.dto.ProductoRequest;
import com.um.prog2.businessservice.model.Categoria;
import com.um.prog2.businessservice.model.Inventario;
import com.um.prog2.businessservice.model.Producto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoBusinessService {

    private final DataServiceClient dataServiceClient;

    public ProductoBusinessService(DataServiceClient dataServiceClient) {
        this.dataServiceClient = dataServiceClient;
    }

    public List<ProductoDTO> obtenerTodosLosProductos() {
        return dataServiceClient.obtenerTodosLosProductos().stream()
                .map(this::mapearAProductoDTO)
                .collect(Collectors.toList());
    }

    public ProductoDTO obtenerProductoPorId(Long id) {
        Producto producto = dataServiceClient.obtenerProductoPorId(id);
        return mapearAProductoDTO(producto);
    }

    public ProductoDTO crearProducto(ProductoRequest request) {
        // Aquí irían validaciones de negocio, por ejemplo:
        // if (request.getPrecio().compareTo(BigDecimal.ZERO) <= 0) { ... }

        // Buscamos la categoría en el data-service para asegurar que existe
        Categoria categoria = dataServiceClient.obtenerCategoriaPorNombre(request.getCategoriaNombre());
        if (categoria == null) {
            // Aquí podrías lanzar una excepción personalizada
            throw new RuntimeException("La categoría especificada no existe");
        }

        // Creamos y guardamos el producto a través del cliente Feign
        Producto nuevoProducto = dataServiceClient.crearProducto(request);
        return mapearAProductoDTO(nuevoProducto);
    }

    public List<ProductoDTO> obtenerProductosPorCategoria(String nombre) {
        return dataServiceClient.obtenerProductosPorCategoria(nombre).stream()
                .map(this::mapearAProductoDTO)
                .collect(Collectors.toList());
    }

    public List<ProductoDTO> obtenerProductosConStockBajo() {
        // El data-service nos da una lista de Inventario, la convertimos a ProductoDTO
        return dataServiceClient.obtenerProductosConStockBajo().stream()
                .map(Inventario::getProducto) // De cada Inventario, sacamos el Producto
                .map(this::mapearAProductoDTO) // Mapeamos cada Producto a ProductoDTO
                .collect(Collectors.toList());
    }

    public BigDecimal calcularValorTotalInventario() {
        return dataServiceClient.obtenerTodoElInventario().stream()
                // Por cada item de inventario, multiplicamos su cantidad por el precio de su producto
                .map(inv -> inv.getProducto().getPrecio().multiply(new BigDecimal(inv.getCantidad())))
                // Sumamos todos los subtotales
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Este es nuestro método "traductor" centralizado
    private ProductoDTO mapearAProductoDTO(Producto producto) {
        if (producto == null) return null;
        return new ProductoDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getCategoria() != null ? producto.getCategoria().getNombre() : "Sin Categoría",
                producto.getInventario() != null ? producto.getInventario().getCantidad() : 0
        );
    }
}

