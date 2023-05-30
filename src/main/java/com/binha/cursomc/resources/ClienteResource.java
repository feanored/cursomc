package com.binha.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.binha.cursomc.domain.Cliente;
import com.binha.cursomc.dto.ClienteDTO;
import com.binha.cursomc.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	ClienteService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable Integer id) {
		Cliente obj = service.buscar(id);
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> buscarTodas() {
		List<Cliente> list = service.buscarTodas();
		List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).toList();
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> atualizar(@Valid @RequestBody ClienteDTO dto, 
			@PathVariable Integer id) {
		Cliente obj = service.fromDTO(dto);
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
	public ResponseEntity<Page<ClienteDTO>> buscarPagina(
			@RequestParam(value="p", defaultValue="0") Integer page, 
			@RequestParam(value="l", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="o", defaultValue="nome") String orderBy, 
			@RequestParam(value="d", defaultValue="ASC") String direction) {
		Page<Cliente> list = service.buscarPagina(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
}
