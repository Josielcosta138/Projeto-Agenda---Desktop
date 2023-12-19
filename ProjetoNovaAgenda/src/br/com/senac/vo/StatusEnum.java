package br.com.senac.vo;

public enum StatusEnum {

	L("Liberado"),
	A("Agendado"),
	AC("Aguardando confirmação");

	private String status;

	private StatusEnum(String descricao) {
		this.status = descricao;
	}

	@Override
	public String toString() {
		return status;
	}

}
