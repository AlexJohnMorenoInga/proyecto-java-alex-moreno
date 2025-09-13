package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.entity.Stock;

import java.util.List;

public interface StockService {

    List<Stock> guardarListaStock(List<Stock> stocks);

    Stock buscarStockPorIdProducto(Long idProducto);

}
