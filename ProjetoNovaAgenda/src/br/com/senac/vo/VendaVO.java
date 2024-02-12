package br.com.senac.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vendas")
public class VendaVO implements Serializable {

    private static final long serialVersionUID = -123456789L;

    @Id
    @NotNull
    @SequenceGenerator(name = "vendas", sequenceName = "sq_vendas", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "vendas")
    private BigInteger id;

    @Basic
    @Column(name = "nome", length = 255)
    private String nome;

    @Basic
    @Column(name = "email", length = 255)
    private String email;

    @Basic
    @Column(name = "dd")
    private String dd;

    @Basic
    @Column(name = "telefone")
    private String telefone;

    @Basic
    @Column(name = "descricao", length = 255)
    private String descricao;

    @Column(name = "valor", precision = 10, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @Column(name = "total", precision = 10, scale = 2, nullable = false)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private StatusFormaPagto statusFormaPagto;

    public VendaVO() {
    	super();
    }

    public VendaVO(BigInteger id, String nome, String email, String dd, String telefone, String descricao,
            BigDecimal valor, int quantidade, BigDecimal total, StatusFormaPagto statusFormaPagto) {
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dd = dd;
        this.telefone = telefone;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
        this.total = total;
        this.statusFormaPagto = statusFormaPagto;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    

    public StatusFormaPagto getStatusFormaPagto() {
        return statusFormaPagto;
    }

    public void setStatusFormaPagto(StatusFormaPagto statusFormaPagto) {
        this.statusFormaPagto = statusFormaPagto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dd, descricao, email, statusFormaPagto, id, nome, quantidade, telefone, total, valor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VendaVO other = (VendaVO) obj;
        return dd == other.dd && Objects.equals(descricao, other.descricao) && Objects.equals(email, other.email)
                && Objects.equals(statusFormaPagto, other.statusFormaPagto) && Objects.equals(id, other.id)
                && Objects.equals(nome, other.nome) && quantidade == other.quantidade
                && Objects.equals(telefone, other.telefone) && Objects.equals(total, other.total)
                && Objects.equals(valor, other.valor);
    }

    @Override
    public String toString() {
        return "Vendas [id=" + id + ", nome=" + nome + ", email=" + email + ", dd=" + dd + ", telefone=" + telefone
                + ", descricao=" + descricao + ", valor=" + valor + ", quantidade=" + quantidade + ", total=" + total
                + ", formaPagto=" + statusFormaPagto + "]";
    }

}
