package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.model.entity.Pedido;
import com.nttdata.dockerized.postgresql.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/users/{idUser}")
    public ResponseEntity<Pedido> guardarPedido(@PathVariable("idUser") Long idUser){
        // Se va a suponer que aqui creamos un objeto de tipo pedido
        Pedido pedido = Pedido.builder()
                //.idPedido()
                .fechaPedido(LocalDateTime.now())
                .estado("Pendiente")
                //.usuario()
                .build();
        // Guardar el pedido usando el servicio
        Pedido pedidoGuardado = pedidoService.guardarPedido(idUser, pedido);
        // Devolver respuesta en el return
        return new ResponseEntity<>(pedidoGuardado, HttpStatus.CREATED);
    }

    @GetMapping("/{idPedido}")
    public ResponseEntity<Pedido> traerPedidoPorId(@PathVariable("idPedido") Long idPedido){

        Pedido pedido = pedidoService.traerPedidoPorId(idPedido);

        return new ResponseEntity<>(pedido, HttpStatus.OK);

    }

}
