package com.um.prog2.dataservice.repository;

import com.um.prog2.dataservice.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    @Query("SELECT i FROM Inventario i WHERE i.cantidad < i.stockMinimo")
    List<Inventario> findByCantidadLessThanStockMinimo();
}

