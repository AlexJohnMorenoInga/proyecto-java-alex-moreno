package com.nttdata.dockerized.postgresql.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.dockerized.postgresql.dto.StockDTO;
import com.nttdata.dockerized.postgresql.model.entity.Stock;
import com.nttdata.dockerized.postgresql.service.StockService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = StockController.class)
class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void guardarListaStocks_retorna201YLista() throws Exception {
        // Datos de entrada (DTO)
        StockDTO dto = new StockDTO();
        dto.setIdProducto(1L);
        dto.setQuantity(10);

        // Entidad que el servicio devolverá
        Stock stockGuardado = new Stock();
        stockGuardado.setIdStock(100L);
        stockGuardado.setIdProducto(1L);
        stockGuardado.setQuantity(10);

        // Mock del servicio
        given(stockService.guardarListaStock(ArgumentMatchers.anyList()))
                .willReturn(List.of(stockGuardado));

        mockMvc.perform(post("/api/stock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(List.of(dto))))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(stockService).guardarListaStock(ArgumentMatchers.anyList());
    }

    @Test
    void traerStockPorIdProducto_retorna200YObjeto() throws Exception {
        Long idProducto = 5L;

        Stock stock = new Stock();
        stock.setIdStock(200L);
        stock.setIdProducto(idProducto);
        stock.setQuantity(25);

        given(stockService.buscarStockPorIdProducto(idProducto))
                .willReturn(stock);

        mockMvc.perform(get("/api/stock/{idProducto}", idProducto))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(stockService).buscarStockPorIdProducto(idProducto);
    }

}