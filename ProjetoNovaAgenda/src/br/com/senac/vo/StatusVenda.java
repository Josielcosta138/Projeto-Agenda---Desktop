package br.com.senac.vo;

public enum StatusVenda {
	
	SIM("Sim"),
	NAO("Não");

	private String status;
	
	StatusVenda(String status) {
		this.status = status;
	}
	
	
	@Override
	public String toString() {
		return status;
	}


	public String getDescricao() {
	
		return status;
	}
	

}
