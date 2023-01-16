package com.binha.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.binha.cursomc.domain.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}
