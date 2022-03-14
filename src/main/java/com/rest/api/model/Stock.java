package com.rest.api.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "stocks")
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "stock_symbol")
    private String stockSymbol;
    @Column(name = "stock_name")
    private String stockName;
    @Column(name = "ask_min")
    private Double askMin;
    @Column(name = "ask_max")
    private Double askMax;
    @Column(name = "bid_min")
    private Double bidMin;
    @Column(name = "bid_max")
    private Double bidMax;
    @CreationTimestamp
    @Column(name = "created_on")
    private Timestamp createdOn;
    @UpdateTimestamp
    @Column(name = "updated_on")
    private Timestamp updatedOn;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stock)) return false;
        Stock stock = (Stock) o;
        return getId().equals(stock.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
