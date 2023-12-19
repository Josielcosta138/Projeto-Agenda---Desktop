package br.com.senac.vo;

public enum StatusServico {

	C("Cabelo"),
	CB("Cabelo e Barba"),
	B("Barba");

	private String status;

	

	private StatusServico(String status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return status;
	}

	
}
