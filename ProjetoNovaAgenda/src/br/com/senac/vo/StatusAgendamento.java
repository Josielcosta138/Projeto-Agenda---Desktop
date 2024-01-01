package br.com.senac.vo;

public enum StatusAgendamento {

	CANCELADO("Cancelado"),
	AGENDADO("Agendado"),
	AGUARDANDO_CONFIRMACAO("Aguardando confirmação");

	private String status;

	private StatusAgendamento(String descricao) {
		this.status = descricao;
	}

	@Override
	public String toString() {
		return status;
	}

}

