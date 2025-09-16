package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.client.PedidoClient;
import com.nttdata.dockerized.postgresql.dto.order.PedidoDTO;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService{

    private final PedidoClient pedidoClient;

    public PedidoServiceImpl(PedidoClient pedidoClient) {
        this.pedidoClient = pedidoClient;
    }

    @Override
    public PedidoDTO traerPedidoPorId(Long idPedido) {
        PedidoDTO pedidoDTO = pedidoClient.findPedidoById(idPedido);
        return pedidoDTO;
    }

}
