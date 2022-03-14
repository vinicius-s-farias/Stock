package com.Rest.API.controller;

import java.io.IOException;
import java.util.List;

import java.util.concurrent.CopyOnWriteArrayList;

import com.Rest.API.Service.GraficoService;
import com.Rest.API.Service.StockService;
import com.Rest.API.dto.StockDto;
import com.Rest.API.model.Stock;
import com.Rest.API.repository.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
     @Autowired
     private StockService stockService;

     public List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

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
    public ResponseEntity<?> teste(@RequestBody StockDto stockDto){
        Stock stock = stockRepository.findById(stockDto.getId()).orElseThrow();
        if(stockDto.getAsk_max() != null) {
            stock.setAsk_max(stockDto.getAsk_max());
            stock.setAsk_min(stockDto.getAsk_min());
        }
        if (stockDto.getBid_min() != null) {
            stock.setBid_max(stockDto.getBid_max());
            stock.setBid_min(stockDto.getBid_min());
        }
        Stock save = stockRepository.save(stock);
        graficoService.AtualizaGrafico(save);
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
    public List <Stock> list(@PathVariable ("stock_name")String stock_name){
        return stockRepository.FindStockName(stock_name);
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

