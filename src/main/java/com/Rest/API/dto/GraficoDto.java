package com.Rest.API.dto;

import com.Rest.API.model.Stock;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class GraficoDto {
    private Stock id_stock;
    private Double open;
    private Double close;
    private Double max;
    private Double min;
    private Timestamp created_on;

    public GraficoDto(Stock id_stock, Double open, Double close, Double max, Double min, Timestamp created_on) {
        this.id_stock = id_stock;
        this.open = open;
        this.close = close;
        this.max = max;
        this.min = min;
        this.created_on = created_on;
    }

}
