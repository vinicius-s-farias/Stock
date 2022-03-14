package com.Rest.API.Service;

import com.Rest.API.model.Grafico;
import com.Rest.API.model.Stock;
import com.Rest.API.repository.GraficoRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Data
@Service
public class GraficoService {
    @Autowired
    private GraficoRepository graficoRepository;

    public void AtualizaGrafico( Stock stocks) {
        System.out.println(stocks.getId());
        Date date = new Date();
        Optional<Grafico> historic2 = graficoRepository .findByIdAndDate(stocks.getId(), new Timestamp(date.getTime()));

        if(historic2.isPresent() ) {
            if (historic2.get().getMax() < stocks.getAsk_min()) {
                historic2.get().setMax(stocks.getAsk_min());
            }
            if (historic2.get().getMin() > stocks.getAsk_min()) {
                historic2.get().setMin(stocks.getAsk_min());
            }
            historic2.get().setClose(stocks.getAsk_min());
            graficoRepository.save(historic2.get());
        }
        else if (stocks.getAsk_min() == null){
            System.out.println("Não pode ser criado");

        }else {
            graficoRepository.save(new Grafico(stocks));
        }
    }

}
