package com.quixada.teste.service;

import com.quixada.teste.model.Endereco;
import com.quixada.teste.model.Frete;

import java.io.BufferedReader;
import java.io.IOException;

public interface FreteService {
    Frete save(Frete frete);

    Endereco buscar(String cep);
    String converteJsonEmString(BufferedReader buffereReader) throws IOException;
}
