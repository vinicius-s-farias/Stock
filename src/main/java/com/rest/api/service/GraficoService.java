package com.rest.api.service;

import com.rest.api.model.Grafico;
import com.rest.api.model.Stock;
import com.rest.api.repository.GraficoRepository;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Data
@Service
public class GraficoService {
    private static final Logger logger = LoggerFactory.getLogger(GraficoService.class);
    @Autowired
    private GraficoRepository graficoRepository;

    public void atualizaGrafico( Stock stocks) {
        Date date = new Date();
        Optional<Grafico> historic2 = graficoRepository .findByIdAndDate(stocks.getId(), new Timestamp(date.getTime()));

        if(historic2.isPresent() ) {
            if (historic2.get().getMax() < stocks.getAskMin()) {
                historic2.get().setMax(stocks.getAskMin());
            }
            if (historic2.get().getMin() > stocks.getAskMin()) {
                historic2.get().setMin(stocks.getAskMin());
            }
            historic2.get().setClose(stocks.getAskMin());
            graficoRepository.save(historic2.get());
        }
        else if (stocks.getAskMin() == null){
            logger.error("Não Pode ser criado");

        }else {
            graficoRepository.save(new Grafico(stocks));
        }
    }

}
