package com.Rest.API.Service;

import com.Rest.API.model.Stock;
import com.Rest.API.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class StockService {

//    @Autowired
//    private StockRepository stockRepository;
//
//    public Page<Stock> acha(Pageable page) {
//
//        return stockRepository.findAll(page);
//    }
}
