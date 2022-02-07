package com.quixada.teste.service.impl;

import com.google.gson.Gson;
import com.quixada.teste.model.Endereco;
import com.quixada.teste.repository.FreteRepository;
import com.quixada.teste.model.Frete;
import com.quixada.teste.service.FreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

@Service
public class FreteServiceImpl implements FreteService {
    @Autowired
    FreteRepository repository;

    private static String cepService = "https://viacep.com.br/ws/";

    public Frete save(Frete frete){
        Endereco origem = buscar(frete.getCepOrigem());
        Endereco destino = buscar(frete.getCepDestino());
        if(origem.getDdd().equals(destino.getDdd())){
            frete.setTotalFrete(frete.getPeso()*0.5);
            frete.setDataPrevistaEntrega(LocalDate.now().plusDays(1));
        }else if(origem.getUf().equals(destino.getUf())){
            frete.setTotalFrete(frete.getPeso()*0.75);
            frete.setDataPrevistaEntrega(LocalDate.now().plusDays(1));
        }else{
            frete.setTotalFrete(frete.getPeso());
            frete.setDataPrevistaEntrega(LocalDate.now().plusDays(1));
        }
        frete.setDataConsulta(LocalDate.now());
        return repository.save(frete);
    }

    @Override
    public Endereco buscar(String cep) {
        String urlParaChamada = cepService+cep+"/json";
        try {
            URL url = new URL(urlParaChamada);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            if (conexao.getResponseCode() != 200)
                throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());

            BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
            String jsonEmString = converteJsonEmString(resposta);

            Gson gson = new Gson();
            Endereco endereco = gson.fromJson(jsonEmString, Endereco.class);

            return endereco;
        } catch (Exception e) {
            return null;
        }
    }

    public String converteJsonEmString(BufferedReader buffereReader) throws IOException {
        String resposta, jsonEmString = "";
        while ((resposta = buffereReader.readLine()) != null) {
            jsonEmString += resposta;
        }
        return jsonEmString;
    }

}
