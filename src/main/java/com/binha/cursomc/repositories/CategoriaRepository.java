package com.binha.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.binha.cursomc.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
