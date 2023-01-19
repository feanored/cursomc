package com.binha.cursomc.dto;

import java.io.Serializable;

import com.binha.cursomc.domain.Categoria;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public CategoriaDTO() {}
	
	public CategoriaDTO(Categoria cat) {
		setId(cat.getId());
		setNome(cat.getNome());
	}

	private Integer id;

	@NotEmpty(message="Preenchimento obrigatório")
	@Size(min = 5, max = 80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
