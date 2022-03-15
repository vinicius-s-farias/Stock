package com.rest.api.repository;

import com.rest.api.model.Stock;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query(value =  " select * from stocks s where ask_min <> 0 and bid_min <> 0 order by updated_on desc fetch first 5 rows only" , nativeQuery = true)
    List<Stock> findStock();

    @Query(value =  " select * from stocks s where stock_name = ?1  " , nativeQuery = true)
    List<Stock> findStockName(String stockName);

    @Query(value =  " select * from stocks s where ask_min <> 0 and bid_min <> 0 order by updated_on desc " , nativeQuery = true)
    Page<Stock> findStock2(Pageable page);
}
