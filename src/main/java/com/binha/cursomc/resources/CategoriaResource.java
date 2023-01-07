package com.binha.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.binha.cursomc.domain.Categoria;
import com.binha.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	CategoriaService service;
	
	@GetMapping(path="/algumas")
	public List<Categoria> listarAlgumas() {
		Categoria cat1 = new Categoria(1, "Informática");
		Categoria cat2 = new Categoria(2, "Escritório");
		
		final List<Categoria> cats = new ArrayList<>();
		cats.add(cat1);
		cats.add(cat2);
		
		return cats;
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> buscar(@PathVariable Integer id) {
		Categoria obj = service.find(id);
		return ResponseEntity.ok(obj);
	}
	
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED)
	public String cadastrar() {
		return "REST is working on POST!";
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED)
	public String atualizar() {
		return "REST is working on PUT!";
	}
	
	@PatchMapping
	@ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED)
	public String resetar() {
		return "REST is working on PATCH!";
	}
	
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED)
	public String deletar() {
		return "REST is working on DELETE!";
	}
}
