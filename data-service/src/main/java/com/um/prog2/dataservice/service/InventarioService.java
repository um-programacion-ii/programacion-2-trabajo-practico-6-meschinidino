package com.um.prog2.dataservice.service;

import com.um.prog2.dataservice.entity.Inventario;
import com.um.prog2.dataservice.repository.InventarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {
    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    public List<Inventario> findAll() {
        return inventarioRepository.findAll();
    }

    public Optional<Inventario> findById(Long id) {
        return inventarioRepository.findById(id);
    }
}
