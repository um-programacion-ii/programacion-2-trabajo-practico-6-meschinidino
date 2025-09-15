package com.um.prog2.businessservice;

import com.um.prog2.businessservice.client.DataServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

// Con esta propiedad, forzamos la URL antes de que Feign intente validarla,
// solucionando el problema de arranque del contexto.
@SpringBootTest(properties = "data.service.url=http://localhost:9999")
class BusinessServiceApplicationTests {

    // Mantenemos el MockBean para que Spring no intente crear una instancia real del cliente.
    @MockBean
    private DataServiceClient dataServiceClient;

    @Test
    void contextLoads() {
    }

}

