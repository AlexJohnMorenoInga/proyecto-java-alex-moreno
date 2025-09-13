package com.nttdata.dockerized.postgresql.client;

import com.nttdata.dockerized.postgresql.model.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "producto-ms", url = "localhost:8090")
public interface ProductoClient {

    @GetMapping("/api/productos/{idProducto}")
    ProductoDTO findProductoById(@PathVariable("idProducto") Long idProducto);

}
