package br.com.senac.vo;

public enum StatusEnum {

	LIBERADO("Liberado"),
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
