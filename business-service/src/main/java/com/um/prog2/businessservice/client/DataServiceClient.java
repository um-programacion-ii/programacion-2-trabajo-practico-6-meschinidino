package com.um.prog2.businessservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "data-service", url = "${data.service.url:http://localhost:8081}")
public interface DataServiceClient {

    @GetMapping("/data/productos")
    String obtenerProductos(); // Temporalmente devuelve un String.
}
