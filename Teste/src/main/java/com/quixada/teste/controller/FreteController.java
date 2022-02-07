package com.quixada.teste.controller;

import com.quixada.teste.model.Frete;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface FreteController {

    @ApiOperation(
            value = "Cadastrar um frete",
            notes = "Este método é responsável por realizar o " +
                    "cadastro de um frete"
    )
    ResponseEntity<Frete> salvar(Frete frete);
}
