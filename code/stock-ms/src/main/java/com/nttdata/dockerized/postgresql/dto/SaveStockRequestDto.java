package com.nttdata.dockerized.postgresql.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveStockRequestDto {

    List<StockDTO> stockDTOS;

}
