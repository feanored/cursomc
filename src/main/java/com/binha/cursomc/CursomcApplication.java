package com.binha.cursomc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.binha.cursomc.domain.Categoria;
import com.binha.cursomc.domain.Cidade;
import com.binha.cursomc.domain.Estado;
import com.binha.cursomc.domain.Produto;
import com.binha.cursomc.repositories.CategoriaRepository;
import com.binha.cursomc.repositories.CidadeRepository;
import com.binha.cursomc.repositories.EstadoRepository;
import com.binha.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}
	
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private EstadoRepository estadoRepo;
	
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@Override
	public void run(String... args) throws Exception {
		List<Categoria> cats = new ArrayList<>();
		cats.add(new Categoria(null, "Informática"));
		cats.add(new Categoria(null, "Escritório"));
		cats.add(new Categoria(null, "Recursos humanos"));
		
		List<Produto> prods = new ArrayList<>();
		prods.add(new Produto(null, "Computador", 2000.0));
		prods.add(new Produto(null, "Impressora", 700.0));
		prods.add(new Produto(null, "Mouse", 80.0));
		
		cats.get(0).getProdutos().addAll(prods);
		cats.get(1).getProdutos().add(prods.get(1));
		cats.get(2).getProdutos().add(prods.get(2));
		
		prods.get(0).getCategorias().add(cats.get(0));
		prods.get(1).getCategorias().addAll(Arrays.asList(cats.get(0), cats.get(1)));
		prods.get(2).getCategorias().addAll(Arrays.asList(cats.get(0), cats.get(2)));
		
		categoriaRepo.saveAll(cats);
		produtoRepo.saveAll(prods);
		
		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", e1);
		Cidade c2 = new Cidade(null, "São Paulo", e2);
		Cidade c3 = new Cidade(null, "Campinas", e2);
		
		e1.getCidades().addAll(Arrays.asList(c1));
		e2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepo.saveAll(Arrays.asList(e1, e2));
		cidadeRepo.saveAll(Arrays.asList(c1, c2, c3));
	}

}
