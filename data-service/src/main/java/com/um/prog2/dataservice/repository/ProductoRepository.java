package com.um.prog2.dataservice.repository;

import com.um.prog2.dataservice.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoriaNombre(String nombre);
}
