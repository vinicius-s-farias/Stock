package com.rest.api.model;

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
    private Stock idStock;
    private Double open;
    private Double close;
    private Double max;
    private Double min;
    @CreationTimestamp
    @Column(name = "created_on")
    private Timestamp createdOn;

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
        this.idStock = stocks;
        this.open = stocks.getAskMin();
        this.close = stocks.getAskMin();
        this.max = stocks.getAskMin();
        this.min = stocks.getAskMin();
        this.createdOn = new Timestamp(date.getTime());
    }
}
