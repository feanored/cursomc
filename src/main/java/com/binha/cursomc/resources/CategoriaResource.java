package com.binha.cursomc.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.binha.cursomc.domain.Categoria;
import com.binha.cursomc.dto.CategoriaDTO;
import com.binha.cursomc.services.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	CategoriaService service;
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> buscarTodas() {
		List<Categoria> list = service.buscarTodas();
		List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).toList();
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Categoria> buscar(@PathVariable Integer id) {
		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}	
	
	@PostMapping
	public ResponseEntity<Void> inserir(@Valid @RequestBody CategoriaDTO dto) {
		Categoria obj = service.inserir(service.fromDTO(dto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> atualizar(@Valid @RequestBody CategoriaDTO dto, @PathVariable Integer id) {
		Categoria obj = service.fromDTO(dto);
		obj.setId(id);
		obj = service.atualizar(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value="/page")
	public ResponseEntity<Page<CategoriaDTO>> buscarPagina(
			@RequestParam(value="p", defaultValue="0") Integer page, 
			@RequestParam(value="l", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="o", defaultValue="nome") String orderBy, 
			@RequestParam(value="d", defaultValue="ASC") String direction) {
		Page<Categoria> list = service.buscarPagina(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
}
