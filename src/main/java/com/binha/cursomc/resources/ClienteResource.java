package com.binha.cursomc.resources;

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

import com.binha.cursomc.domain.Cliente;
import com.binha.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	ClienteService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> buscar(@PathVariable Integer id) {
		Cliente obj = service.find(id);
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED)
	public String listar() {
		return "REST is working on GET!";
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
