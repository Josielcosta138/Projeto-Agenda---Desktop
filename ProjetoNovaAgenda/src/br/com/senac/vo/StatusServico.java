package br.com.senac.vo;

public enum StatusServico {

	CABELO("Cabelo"),
	CABELO_BARBA("Cabelo e Barba"),
	BARBA("Barba");

	private String status;

	private StatusServico(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return status;
	}

	
}
