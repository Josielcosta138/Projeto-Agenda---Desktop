package br.com.senac.vo;

import java.io.Serializable;
import java.math.BigInteger;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;
import java.util.Objects;



@Entity
@Table(name = "evento")
public class EventoVO implements Serializable{
	
	private static final long serialVersionUID = 773715961962132822L;
	
	
	@Id
	@NotNull
	@SequenceGenerator(name = "evento", sequenceName = "sq_evento", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "evento")
    private BigInteger id;

	@Basic
    @Column(name = "datahorainicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date  dataHoraInicio;

	@Basic
    @Column(name = "datahorafim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date  dataHoraFim;

	@Basic
    @Column(name = "local", length = 255)
    private String local;
	
	
	@Basic(optional = true) // Not Null esta "desmarcado" no banco
	@Column(name = "numero", length = 10)
	private String numero;

	@Basic(optional = true) // Not Null esta desmarcado no banco
	@Column(name = "dd", length = 4)
	private String dd;
	
	
	@Basic
	@Column(name = "participantes")
	private BigInteger participantes;

    @Column(name = "status", length = 40)
    private String status;
    
    @Column(name = "tiposervico", length = 100)
    private String tipoServico;
    
    @Column(name = "nomecliente", length = 100)
    private String nomeCliente;
    
    @Column(name = "email", length = 100)
    private String email;

    
    
	public EventoVO() {
		super();
	}

	public EventoVO(BigInteger id, Date  dataHoraInicio, Date  dataHoraFim, String local, BigInteger participantes, String status,
			String ocupadoVago, String tipoServico, String numero, String dd) {
		super();
		this.id = id;
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraFim = dataHoraFim;
		this.local = local;
		this.participantes = participantes;
		this.status = status;
		this.tipoServico = tipoServico;
		this.numero = numero;
		this.dd = dd;
		
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Date  getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(Date  dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public Date  getDataHoraFim() {
		return dataHoraFim;
	}

	public void setDataHoraFim(Date  dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public BigInteger getParticipantes() {
		return participantes;
	}

	 public void setParticipantes(BigInteger participantes) {
	        this.participantes = participantes;
	    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
	public String getDd() {
		return dd;
	}

	public void setDd(String dd) {
		this.dd = dd;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataHoraFim, dataHoraInicio, dd, email, id, local, nomeCliente, numero, participantes,
				status, tipoServico);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventoVO other = (EventoVO) obj;
		return Objects.equals(dataHoraFim, other.dataHoraFim) && Objects.equals(dataHoraInicio, other.dataHoraInicio)
				&& Objects.equals(dd, other.dd) && Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(local, other.local) && Objects.equals(nomeCliente, other.nomeCliente)
				&& Objects.equals(numero, other.numero) && Objects.equals(participantes, other.participantes)
				&& Objects.equals(status, other.status) && Objects.equals(tipoServico, other.tipoServico);
	}

	@Override
	public String toString() {
		return "EventoVO [id=" + id + ", dataHoraInicio=" + dataHoraInicio + ", dataHoraFim=" + dataHoraFim + ", local="
				+ local + ", numero=" + numero + ", dd=" + dd + ", participantes=" + participantes + ", status="
				+ status + ", tipoServico=" + tipoServico + ", nomeCliente=" + nomeCliente + ", email=" + email + "]";
	}




} 
