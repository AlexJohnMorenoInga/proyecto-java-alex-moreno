package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.dto.order.PedidoDTO;

public interface PedidoService {

    PedidoDTO traerPedidoPorId(Long idPedido);

}
