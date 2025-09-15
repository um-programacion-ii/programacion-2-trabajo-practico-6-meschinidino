package com.um.prog2.dataservice.repository;

import com.um.prog2.dataservice.entity.Inventario;
import com.um.prog2.dataservice.entity.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class InventarioRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private InventarioRepository inventarioRepository;

    @Test
    void cuandoGuardaYBuscaPorId_entoncesRetornaInventarioCorrecto() {
        // Arrange
        Producto producto = new Producto();
        producto.setNombre("Silla Gamer");
        producto.setPrecio(new BigDecimal("300.50"));
        entityManager.persist(producto);

        Inventario inventario = new Inventario();
        inventario.setProducto(producto);
        inventario.setCantidad(50);
        entityManager.persist(inventario);
        entityManager.flush();

        // Act
        Optional<Inventario> inventarioEncontrado = inventarioRepository.findById(inventario.getId());

        // Assert
        assertTrue(inventarioEncontrado.isPresent());
        assertEquals(50, inventarioEncontrado.get().getCantidad());
        assertEquals("Silla Gamer", inventarioEncontrado.get().getProducto().getNombre());
    }
}
