package com.nttdata.dockerized.postgresql.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockDTOc {

    private Long idProducto;

    private Integer quantity;

}
