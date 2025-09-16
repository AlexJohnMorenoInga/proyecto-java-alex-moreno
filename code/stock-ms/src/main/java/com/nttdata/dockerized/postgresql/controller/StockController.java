package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.dto.*;
import com.nttdata.dockerized.postgresql.model.entity.Stock;
import com.nttdata.dockerized.postgresql.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.nttdata.dockerized.postgresql.mapper.StockMapper.INSTANCE;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<List<StockDTOb>> guardarListaStocks(@RequestBody List<StockDTO> stockDTOS){

        List<Stock> stocksGuardados = stockService.guardarListaStock(INSTANCE.toStocks(stockDTOS));

        return new ResponseEntity<>(INSTANCE.toStockDTObs(stocksGuardados), HttpStatus.CREATED);

    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<StockDTOc> traerStockPorIdProducto(@PathVariable("idProducto") Long idProducto){

        Stock stock = stockService.buscarStockPorIdProducto(idProducto);

        return new ResponseEntity<>(INSTANCE.toStockDTOc(stock), HttpStatus.OK);

    }

}
