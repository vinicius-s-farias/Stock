package com.Rest.API.repository;

import com.Rest.API.model.Grafico;

import com.Rest.API.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


public interface GraficoRepository  extends JpaRepository<Grafico, Long> {

    @Query( nativeQuery = true, value = """
    select * from grafico as shp where
    shp.id_stock = :id_stock and 
    date_trunc('hour', shp.created_on) = date_trunc('hour', cast (:now as Timestamp))
""")
    Optional<Grafico> findByIdAndDate(@Param("id_stock") Long id_stock, @Param("now") Timestamp agora);

    @Query(value =  "  select * from grafico usb where id_stock = ?1 " , nativeQuery = true)
    List<Grafico> FinGrafico(Stock id_stock);
}

