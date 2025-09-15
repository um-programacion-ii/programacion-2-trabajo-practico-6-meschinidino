package com.um.prog2.dataservice.controller;

import com.um.prog2.dataservice.entity.Categoria;
import com.um.prog2.dataservice.entity.Inventario;
import com.um.prog2.dataservice.entity.Producto;
import com.um.prog2.dataservice.repository.CategoriaRepository;
import com.um.prog2.dataservice.repository.InventarioRepository;
import com.um.prog2.dataservice.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// @SpringBootTest levanta el contexto completo de la aplicación para una prueba de integración.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @ActiveProfiles("test") le dice a Spring que use el application-test.properties para la configuración.
@ActiveProfiles("test")
class DataControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate; // Cliente HTTP para hacer llamadas en los tests.

    // Inyectamos los repositorios para poder manipular la base de datos directamente en los tests.
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    // Este método se ejecuta antes de cada test para asegurar que la base de datos esté limpia.
    @BeforeEach
    void setUp() {
        // El orden de borrado es importante por las relaciones (foreign keys).
        inventarioRepository.deleteAll();
        productoRepository.deleteAll();
        categoriaRepository.deleteAll();
    }

    @Test
    void cuandoPeticionAProductos_entoncesRetornaJsonConListaDeProductos() {
        // Arrange: Guardamos datos en la BD de prueba ANTES de hacer la llamada HTTP.
        Categoria categoriaGuardada = categoriaRepository.save(new Categoria(null, "Electrónica", "Dispositivos electrónicos", null));
        productoRepository.save(new Producto(null, "Laptop", "Laptop de alto rendimiento", new BigDecimal("1500.00"), categoriaGuardada, null));

        // Act: Hacemos la llamada al endpoint /data/productos.
        ResponseEntity<List<Producto>> response = restTemplate.exchange(
                "/data/productos",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Producto>>() {});

        // Assert: Verificamos que la respuesta es correcta.
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(1); // Ahora debería encontrar el producto que guardamos.
        assertThat(response.getBody().get(0).getNombre()).isEqualTo("Laptop");
    }

    @Test
    void cuandoPeticionACategorias_entoncesRetornaJsonConListaDeCategorias() {
        // Arrange
        categoriaRepository.save(new Categoria(null, "Ropa", "Prendas de vestir", null));

        // Act
        ResponseEntity<List<Categoria>> response = restTemplate.exchange(
                "/data/categorias",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Categoria>>() {});

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).getNombre()).isEqualTo("Ropa");
    }

    @Test
    void cuandoPeticionAInventario_entoncesRetornaJsonConListaDeInventario() {
        // Arrange: Necesitamos guardar una categoría y un producto primero.
        Categoria categoriaGuardada = categoriaRepository.save(new Categoria(null, "Periféricos", "Accesorios para PC", null));
        Producto productoGuardado = productoRepository.save(new Producto(null, "Mouse Gamer", "Mouse con RGB", new BigDecimal("80.00"), categoriaGuardada, null));
        inventarioRepository.save(new Inventario(null, productoGuardado, 100, 10, null));

        // Act
        ResponseEntity<List<Inventario>> response = restTemplate.exchange(
                "/data/inventario",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Inventario>>() {});

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).getCantidad()).isEqualTo(100);
    }
}

