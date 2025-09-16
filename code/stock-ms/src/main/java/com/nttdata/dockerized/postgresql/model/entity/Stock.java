package com.nttdata.dockerized.postgresql.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_stock")
    private Long idStock;

    @Column(name="id_producto", unique = true, nullable = false)
    private Long idProducto; //

    @Column(name="id_warehouse")
    private Long idWareHouse; //

    private Integer quantity;

}
