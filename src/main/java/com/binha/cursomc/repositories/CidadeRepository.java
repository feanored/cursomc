package com.binha.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.binha.cursomc.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
