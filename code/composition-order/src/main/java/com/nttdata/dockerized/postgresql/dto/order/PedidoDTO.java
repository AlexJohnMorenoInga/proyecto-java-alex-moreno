package com.nttdata.dockerized.postgresql.dto.order;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private Long idPedido;

    private LocalDateTime fechaPedido;

    private String estado;

}
