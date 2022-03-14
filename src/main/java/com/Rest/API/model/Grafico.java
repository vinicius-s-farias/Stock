package com.Rest.API.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "Grafico")
public class Grafico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_stock")
    private Stock id_stock;
    private Double open;
    private Double close;
    private Double max;
    private Double min;
    @CreationTimestamp
    @Column(name = "created_on")
    private Timestamp created_on;

    public Grafico() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grafico)) return false;
        Grafico grafico = (Grafico) o;
        return Objects.equals(getId(), grafico.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public Grafico(Stock stocks) {
        Date date = new Date();
        this.id_stock = stocks;
        this.open = stocks.getAsk_min();
        this.close = stocks.getAsk_min();
        this.max = stocks.getAsk_min();
        this.min = stocks.getAsk_min();
        this.created_on = new Timestamp(date.getTime());
    }
}
