package com.rest.api.dto;

import com.rest.api.model.Stock;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class GraficoDto {
    private Stock idStock;
    private Double open;
    private Double close;
    private Double max;
    private Double min;
    private Timestamp createdOn;

    public GraficoDto(Stock idStock, Double open, Double close, Double max, Double min, Timestamp createdOn) {
        this.idStock = idStock;
        this.open = open;
        this.close = close;
        this.max = max;
        this.min = min;
        this.createdOn = createdOn;
    }

}
