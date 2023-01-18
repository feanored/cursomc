package com.binha.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.binha.cursomc.domain.Categoria;
import com.binha.cursomc.repositories.CategoriaRepository;
import com.binha.cursomc.services.exceptions.DataIntegrityException;
import com.binha.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
						"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())
				);
	}
	
	public Categoria inserir(Categoria obj) {
		obj.setId(null); //TODO vou trocar isso por uma validação
		return repo.save(obj);
	}
	
	public Categoria atualizar(Categoria obj) {
		buscar(obj.getId());
		return repo.save(obj);
	}
	
	public void deletar(Integer id) {
		buscar(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Esta categoria não pode ser excluída pois contém produtos associados.");
		}
	}
}
