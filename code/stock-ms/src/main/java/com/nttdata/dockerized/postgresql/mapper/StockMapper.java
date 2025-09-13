package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.dto.*;
import com.nttdata.dockerized.postgresql.model.entity.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StockMapper {

    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    List<Stock> toStocks(List<StockDTO> stockDTOS);

    List<StockDTOb> toStockDTObs(List<Stock> stocks);

    StockDTOc toStockDTOc(Stock stock);

}
