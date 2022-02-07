package com.quixada.teste.repository;

import com.quixada.teste.model.Frete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Integer>{
}
