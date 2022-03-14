package com.Rest.API.repository;

import com.Rest.API.model.Stock;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query(value =  " select * from stocks s where ask_min <> 0 and bid_min <> 0 order by updated_on desc fetch first 5 rows only" , nativeQuery = true)
    List<Stock> FindStock();

    @Query(value =  " select * from stocks s where stock_name = ?1  " , nativeQuery = true)
    List<Stock> FindStockName(String stock_name);

    @Query(value =  " select * from stocks s where ask_min <> 0 and bid_min <> 0 order by updated_on desc " , nativeQuery = true)
    Page<Stock> FindStock2(Pageable page);
}
