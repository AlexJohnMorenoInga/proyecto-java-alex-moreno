package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.exceptions.BadRequestException;
import com.nttdata.dockerized.postgresql.exceptions.ResourceNotFoundException;
import com.nttdata.dockerized.postgresql.model.entity.Stock;
import com.nttdata.dockerized.postgresql.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StockServiceImpl implements StockService{

    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public List<Stock> guardarListaStock(List<Stock> stocks) {

        //Recorrer la lista para validar los datos
        for(Stock stock : stocks){
            if(stock.getIdProducto() == 0){
                throw new BadRequestException("El id de producto no puede ser cero");
            }
            if(stock.getIdWareHouse() == 0){
                throw new BadRequestException("El id de warehouse no puede ser cero");
            }
            if(stock.getQuantity() == 0){
                throw new BadRequestException("La cantidad a ingresar no puede ser cero");
            }
        }

        // Guadar los datos
        Iterable<Stock> iterable = stockRepository.saveAll(stocks);

        // Devolver una respuesta
        return StreamSupport
                .stream(iterable.spliterator(), false)
                .collect(Collectors.toList());

    }

    @Override
    public Stock buscarStockPorIdProducto(Long idProducto) {

        Stock stock = stockRepository.findStockByIdProducto(idProducto).orElseThrow(
                () -> new ResourceNotFoundException("No existe stock del id de producto indicado")
        );

        return stock ;
    }

}
