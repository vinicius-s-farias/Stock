package com.Rest.API.controller;


import com.Rest.API.Service.GraficoService;
import com.Rest.API.model.Grafico;
import com.Rest.API.model.Stock;
import com.Rest.API.repository.GraficoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class GraficoController {

    @Autowired
    private GraficoRepository graficoRepository;



    @GetMapping("/grafico/{id_Stock}")
    public List<Grafico> PegaGrafico(@PathVariable (value = "id_Stock") Stock id_Stock ) {
        return graficoRepository.FinGrafico(id_Stock);
    }
}
