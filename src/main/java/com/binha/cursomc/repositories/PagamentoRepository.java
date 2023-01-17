package com.binha.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.binha.cursomc.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
