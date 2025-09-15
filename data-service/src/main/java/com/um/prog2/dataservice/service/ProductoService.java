package com.um.prog2.dataservice.service;

import com.um.prog2.dataservice.entity.Producto;
import com.um.prog2.dataservice.exception.ResourceNotFoundException;
import com.um.prog2.dataservice.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Producto findById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
    }

    @Transactional
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Transactional
    public Producto update(Long id, Producto productoDetails) {
        Producto producto = findById(id); // Reutilizamos el findById para asegurar que exista
        producto.setNombre(productoDetails.getNombre());
        producto.setDescripcion(productoDetails.getDescripcion());
        producto.setPrecio(productoDetails.getPrecio());
        producto.setCategoria(productoDetails.getCategoria());
        return productoRepository.save(producto);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado con id: " + id);
        }
        productoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Producto> findByCategoriaNombre(String nombre) {
        return productoRepository.findByCategoriaNombre(nombre);
    }
}
