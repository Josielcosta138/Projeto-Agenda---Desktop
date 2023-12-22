package br.com.senac.vo;

public enum StatusDisponibilidade {
	
	
	LIVRE("Livre"),
	VAGO("Vago");

	private String status;


	private StatusDisponibilidade(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return status;
	}

}
