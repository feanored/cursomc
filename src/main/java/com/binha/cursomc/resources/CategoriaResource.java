package com.binha.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.binha.cursomc.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@GetMapping
	public List<Categoria> listar() {
		final List<Categoria> cats = new ArrayList<>();
		cats.add(new Categoria(1, "Informática"));
		cats.add(new Categoria(2, "Escritório"));
		
		return cats;
	}
	
	@PostMapping
	public String cadastrar() {
		return "REST is working on POST!";
	}
	
	@PutMapping
	public String atualizar() {
		return "REST is working on PUT!";
	}
	
	@DeleteMapping
	public String deletar() {
		return "REST is working on DELETE!";
	}
}
