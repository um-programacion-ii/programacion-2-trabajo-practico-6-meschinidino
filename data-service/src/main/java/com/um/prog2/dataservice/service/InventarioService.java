package com.um.prog2.dataservice.service;

import com.um.prog2.dataservice.entity.Inventario;
import com.um.prog2.dataservice.exception.ResourceNotFoundException;
import com.um.prog2.dataservice.repository.InventarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventarioService {
    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    @Transactional(readOnly = true)
    public List<Inventario> findAll() {
        return inventarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Inventario findById(Long id) {
        return inventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado con id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Inventario> findProductosConStockBajo() {
        return inventarioRepository.findByCantidadLessThanStockMinimo();
    }
}

