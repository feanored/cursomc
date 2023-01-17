package com.binha.cursomc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.binha.cursomc.domain.Categoria;
import com.binha.cursomc.domain.Cidade;
import com.binha.cursomc.domain.Cliente;
import com.binha.cursomc.domain.Endereco;
import com.binha.cursomc.domain.Estado;
import com.binha.cursomc.domain.ItemPedido;
import com.binha.cursomc.domain.Pagamento;
import com.binha.cursomc.domain.PagamentoComBoleto;
import com.binha.cursomc.domain.PagamentoComCartao;
import com.binha.cursomc.domain.Pedido;
import com.binha.cursomc.domain.Produto;
import com.binha.cursomc.domain.StatusPagamento;
import com.binha.cursomc.domain.TipoCliente;
import com.binha.cursomc.repositories.CategoriaRepository;
import com.binha.cursomc.repositories.CidadeRepository;
import com.binha.cursomc.repositories.ClienteRepository;
import com.binha.cursomc.repositories.EnderecoRepository;
import com.binha.cursomc.repositories.EstadoRepository;
import com.binha.cursomc.repositories.ItemPedidoRepository;
import com.binha.cursomc.repositories.PagamentoRepository;
import com.binha.cursomc.repositories.PedidoRepository;
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
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private EnderecoRepository enderecoRepo;
	
	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private PagamentoRepository pagamentoRepo;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;
	
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
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "28398734873", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("123120312", "490994965"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "24832894", cli1, c1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "35495651", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		clienteRepo.save(cli1);
		enderecoRepo.saveAll(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, StatusPagamento.PAGO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, StatusPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepo.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepo.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, prods.get(0), 0.0, 1, 2000.0);
		ItemPedido ip2 = new ItemPedido(ped1, prods.get(2), 0.0, 2, 80.0);
		ItemPedido ip3 = new ItemPedido(ped2, prods.get(1), 100.0, 1, 800.0);
		
		ped1.getItensPedido().addAll(Arrays.asList(ip1, ip2));
		ped2.getItensPedido().add(ip3);
		
		prods.get(0).getItensPedido().add(ip1);
		prods.get(1).getItensPedido().add(ip3);
		prods.get(2).getItensPedido().add(ip2);
		
		itemPedidoRepo.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
