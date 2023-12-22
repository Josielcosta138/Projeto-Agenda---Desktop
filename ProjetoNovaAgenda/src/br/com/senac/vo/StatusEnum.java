package br.com.senac.vo;

public enum StatusEnum {

	CANCELADO("Cancelado"),
	AGENDADO("Agendado"),
	AGUARDANDO_CONFIRMACAO("Aguardando confirmação");

	private String status;

	private StatusEnum(String descricao) {
		this.status = descricao;
	}

	@Override
	public String toString() {
		return status;
	}

}
