package br.com.senac.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
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
@Table(name = "produtos")
public class ProdutoVO implements Serializable{

 
	private static final long serialVersionUID = -6251495979819636652L;

	@Id
	@NotNull
	@SequenceGenerator(name = "produtos", sequenceName = "sq_produtos", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "produtos")
    private BigInteger id;

	@Basic
	@Column(name = "indentificacao")
	private BigInteger indentificacao;
	
	@Basic
	@Column(name = "nome", length = 255)
    private String nome;


	@Basic
	@Column(name = "marca", length = 255)
    private String marca;

	@Basic
	@Column(name = "descricao", length = 255)
    private String descricao;

    @Column(name = "preco", precision = 10, scale = 2, nullable = false)
    private BigDecimal preco;

    @Column(name = "quantidadeestoque", nullable = false)
    private int quantidadeEstoque;

    @Basic
	@Column(name = "datavalidade", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
    private Date dataValidade;
    
    

	public ProdutoVO() {
		super();
	}

	public ProdutoVO(BigInteger id, BigInteger indentificacao, String nome, String marca, String descricao,
			BigDecimal preco, int quantidadeEstoque, Date dataValidade) {
		super();
		this.id = id;
		this.indentificacao = indentificacao;
		this.nome = nome;
		this.marca = marca;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidadeEstoque = quantidadeEstoque;
		this.dataValidade = dataValidade;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigInteger getIndentificacao() {
		return indentificacao;
	}

	public void setIndentificacao(BigInteger indentificacao) {
		this.indentificacao = indentificacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataValidade, descricao, id, indentificacao, marca, nome, preco, quantidadeEstoque);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoVO other = (ProdutoVO) obj;
		return Objects.equals(dataValidade, other.dataValidade) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(id, other.id) && indentificacao == other.indentificacao
				&& Objects.equals(marca, other.marca) && Objects.equals(nome, other.nome)
				&& Objects.equals(preco, other.preco) && quantidadeEstoque == other.quantidadeEstoque;
	}

	@Override
	public String toString() {
		return "ProdutoVO [id=" + id + ", indentificacao=" + indentificacao + ", nome=" + nome
				+ ", marca=" + marca + ", descricao=" + descricao + ", preco=" + preco + ", quantidadeEstoque="
				+ quantidadeEstoque + ", dataValidade=" + dataValidade + "]";
	}
	
	
	

   
    

}
