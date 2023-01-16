package com.binha.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.binha.cursomc.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
