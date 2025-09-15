package com.um.prog2.dataservice.repository;

import com.um.prog2.dataservice.entity.Categoria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoriaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    void cuandoGuardaYBuscaPorId_entoncesRetornaCategoriaCorrecta() {
        // Arrange
        Categoria categoria = new Categoria();
        categoria.setNombre("Hogar");
        entityManager.persist(categoria);
        entityManager.flush();

        // Act
        Optional<Categoria> categoriaEncontrada = categoriaRepository.findById(categoria.getId());

        // Assert
        assertTrue(categoriaEncontrada.isPresent());
        assertEquals("Hogar", categoriaEncontrada.get().getNombre());
    }
}
