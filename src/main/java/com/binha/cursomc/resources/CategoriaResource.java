package com.binha.cursomc.resources;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@GetMapping
	public String listar() {
		return "REST is working on GET!";
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
