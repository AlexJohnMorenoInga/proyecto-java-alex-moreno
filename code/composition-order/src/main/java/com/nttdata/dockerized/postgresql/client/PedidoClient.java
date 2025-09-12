package com.nttdata.dockerized.postgresql.client;

import com.nttdata.dockerized.postgresql.dto.order.PedidoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "orden-ms", url = "localhost:8080")
public interface PedidoClient {

    @GetMapping("/api/pedidos/{idPedido}")
    PedidoDTO findPedidoById(@PathVariable("idPedido") Long idPedido);

}
