package br.com.senac.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tiposervico")
public class TipoServicoVO implements Serializable {

	private static final long serialVersionUID = 3070739047847466677L;

	@Id
	@NotNull
	@SequenceGenerator(name = "tiposervico", sequenceName = "sq_tiposervico", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "tiposervico")
	private BigInteger id;

	@Basic
	@Column(name = "nome", length = 255)
	private String nome;

	@Column(name = "valor", precision = 10, scale = 2, nullable = false)
	private BigDecimal valor;

	@Column(name = "duracao", nullable = false)
	private Integer duracao;
	
	

	public TipoServicoVO() {
		super();
	}



	public TipoServicoVO(BigInteger id, String nome, BigDecimal valor, Integer duracao) {
		super();
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.duracao = duracao;
	}



	public BigInteger getId() {
		return id;
	}



	public void setId(BigInteger id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public BigDecimal getValor() {
		return valor;
	}



	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}



	public Integer getDuracao() {
		return duracao;
	}



	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}



	@Override
	public int hashCode() {
		return Objects.hash(duracao, id, nome, valor);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoServicoVO other = (TipoServicoVO) obj;
		return Objects.equals(duracao, other.duracao) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(valor, other.valor);
	}



	@Override
	public String toString() {
		return "TipoServico [id=" + id + ", nome=" + nome + ", valor=" + valor + ", duracao=" + duracao + "]";
	}
	
	
	
	

}
