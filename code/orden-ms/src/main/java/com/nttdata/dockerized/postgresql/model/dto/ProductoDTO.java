package com.nttdata.dockerized.postgresql.model.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

    private Long idProducto;

    private String nombre;

    private Double precio;

}
