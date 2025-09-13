package com.nttdata.dockerized.postgresql.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockDTOb {

    private Long idStock;

    private Long idProducto;

    private Long idWareHouse;

    private Integer quantity;

}
