package com.um.prog2.dataservice.repository;

import com.um.prog2.dataservice.entity.Categoria;
import com.um.prog2.dataservice.entity.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductoRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void cuandoGuardaYBuscaPorId_entoncesRetornaProductoCorrecto() {
        // Arrange
        Categoria categoria = new Categoria();
        categoria.setNombre("Tecnología");
        entityManager.persist(categoria);

        Producto producto = new Producto();
        producto.setNombre("Laptop Pro");
        producto.setPrecio(new BigDecimal("1200.00"));
        producto.setCategoria(categoria);
        entityManager.persist(producto);
        entityManager.flush();

        // Act
        Optional<Producto> productoEncontrado = productoRepository.findById(producto.getId());

        // Assert
        assertTrue(productoEncontrado.isPresent());
        assertEquals("Laptop Pro", productoEncontrado.get().getNombre());
    }
}
