package br.com.senac.vo;

public enum StatusServico {

	CABELO("Cabelo"),
	CABELO_BARBA("Cabelo e Barba"),
	BARBA("Barba"),
	HIDRATACAO_CAPILAR("Hidratação Capilar"),
	LIMPEZA_SOMBRANCELHA("Limpeza de sombrancelha");

	private String status;

	private StatusServico(String status) {
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
