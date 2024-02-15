package br.com.senac.vo;

public enum StatusAgendamento {

	CANCELADO("Cancelado"),
	AGENDADO("Agendado"),
	AGUARDANDO_CONFIRMACAO("Aguardando confirmação");

	private String status;

	private StatusAgendamento(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return status;
	}

	public String getStatus() {
		return status;
	}
	
	

}

