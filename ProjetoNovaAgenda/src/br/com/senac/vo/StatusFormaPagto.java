package br.com.senac.vo;

public enum StatusFormaPagto {
	
	DINHEIRO("Dinheiro"),
	CARTAO("Cartão"),
	PIX("Pix");
	
	private String formPagto;
	
	private StatusFormaPagto(String formPagto) {
		this.formPagto = formPagto;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	

}
