package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.dto.order.PedidoDTO;
import com.nttdata.dockerized.postgresql.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/composition")
public class CompositionController {

    private final PedidoService pedidoService;

    public CompositionController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/{idPedido}")
    public ResponseEntity<PedidoDTO> traerPedidoPorId(@PathVariable("idPedido") Long idPedido){
        PedidoDTO pedidoDTO = pedidoService.traerPedidoPorId(idPedido);
        return new ResponseEntity<>(pedidoDTO, HttpStatus.OK);
    }

}
