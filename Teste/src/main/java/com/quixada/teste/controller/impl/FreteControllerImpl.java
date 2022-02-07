package com.quixada.teste.controller.impl;

import com.quixada.teste.controller.FreteController;
import com.quixada.teste.model.Frete;
import com.quixada.teste.service.impl.FreteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fretes")
public class FreteControllerImpl implements FreteController {



    @Autowired
    FreteServiceImpl service;

    @PostMapping
    public ResponseEntity<Frete> salvar(@RequestBody Frete frete){
        System.out.println("entrou");
        return ResponseEntity.ok(service.save(frete));
    }
}
