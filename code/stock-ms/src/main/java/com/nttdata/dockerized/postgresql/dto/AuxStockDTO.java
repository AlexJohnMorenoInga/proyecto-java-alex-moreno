package com.nttdata.dockerized.postgresql.dto;

import com.nttdata.dockerized.postgresql.model.entity.Stock;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuxStockDTO {

    List<Stock> stocks;

}
