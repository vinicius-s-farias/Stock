package com.rest.api.controller;

import com.rest.api.model.Grafico;
import com.rest.api.model.Stock;
import com.rest.api.repository.GraficoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:8082")
@RestController
public class GraficoController {

    @Autowired
    private GraficoRepository graficoRepository;


    @GetMapping("/grafico/{id_Stock}")
    public List<Grafico> getGraphic(@PathVariable (value = "id_Stock") Stock idStock ) {
        return graficoRepository.finGrafico(idStock);
    }


}
