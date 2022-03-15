package com.rest.api.controller;

import java.io.IOException;
import java.util.List;

import java.util.concurrent.CopyOnWriteArrayList;

import com.rest.api.model.Stock;
import com.rest.api.repository.StockRepository;
import com.rest.api.service.GraficoService;
import com.rest.api.dto.StockDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


import javax.servlet.http.HttpServletResponse;



@CrossOrigin
@RequestMapping("/stocks")
@RestController
public class StockController {
    @Autowired
    private GraficoService graficoService;
     @Autowired
     private StockRepository stockRepository;

     private List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

     @GetMapping(value = "/subscribe")
        public SseEmitter subscribe(HttpServletResponse response){
         response.setHeader("Cache_Control", "no-store" );
         SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);

         try {
             this.emitters.add(sseEmitter);
         }catch (Exception e){
             e.printStackTrace();
         }
         sseEmitter.onCompletion(() -> this.emitters.remove(sseEmitter));
         return sseEmitter;
     }

    @PostMapping("/teste")
    public ResponseEntity<Stock> getStock(@RequestBody StockDto stockDto){
        Stock stock = stockRepository.findById(stockDto.getId()).orElseThrow();
        if(stockDto.getAskMax() != null) {
            stock.setAskMax(stockDto.getAskMax());
            stock.setAskMin(stockDto.getAskMin());
        }
        if (stockDto.getBidMin() != null) {
            stock.setBidMax(stockDto.getBidMax());
            stock.setBidMin(stockDto.getBidMin());
        }
        Stock save = stockRepository.save(stock);
        graficoService.atualizaGrafico(save);
        publicar();
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }


    public void publicar(){
        for (SseEmitter emitter: emitters){
            try{
                emitter.send((stockRepository.FindStock()));
            }catch (IOException e){
                emitters.remove(emitter);
            }
        }
    }

    @GetMapping("")
    public List<Stock> listar() {
        return stockRepository.FindStock();
    }

    @GetMapping("/{stock_name}")
    public List <Stock> list(@PathVariable ("stock_name")String stockName){
        return stockRepository.FindStockName(stockName);
    }



    @GetMapping("/all")
    public List<Stock> acha2() {
        return stockRepository.findAll();
    }

    @GetMapping("/page")
    Page<Stock> getStock(Pageable page){
        return stockRepository.FindStock2(page);
    }

//    aaa
}

