package com.binha.cursomc.domain.enums;

public enum StatusPagamento {
	
	PENDENTE(1, "Pendente"),
	PAGO(2, "Pago"),
	CANCELADO(3, "Cancelado");
	
	private int codigo;
	private String descricao;
	
	private StatusPagamento(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public static StatusPagamento toEnum(Integer codigo) {
		if (codigo == null) return null;
		
		for (StatusPagamento t : StatusPagamento.values())
			if (codigo.equals(t.getCodigo()))
				return t;
		
		throw new IllegalArgumentException("Tipo inválido para o Id: "+codigo);
	}
}
