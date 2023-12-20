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
	
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	/*
    @ElementCollection
    @CollectionTable(name = "evento", joinColumns = @JoinColumn(name = "contat_id"))
    @Column(name = "participantes")
    private List<BigInteger> participantes; */
	
	
	@Basic
	@Column(name = "participantes")
	private BigInteger participantes;

    @Column(name = "status", length = 20)
    private String status;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "contat_id")
    private ContatoVO contat;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "contel_id")
    private ContelVO contel;
    
    
    @Column(name = "tiposervico", length = 100)
    private String tipoServico;

    
    
    

    
	public EventoVO() {
		super();
	}

	public EventoVO(BigInteger id, Date  dataHoraInicio, Date  dataHoraFim, String local, BigInteger participantes, String status,
			String ocupadoVago, ContatoVO contat, ContelVO contel, String tipoServico) {
		super();
		this.id = id;
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraFim = dataHoraFim;
		this.local = local;
		this.participantes = participantes;
		this.status = status;
		this.contat = contat;
		this.contel = contel;
		this.tipoServico = tipoServico;
		
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

	

	public ContatoVO getContat() {
		return contat;
	}

	public void setContat(ContatoVO contat) {
		this.contat = contat;
	}

	public ContelVO getContel() {
		return contel;
	}

	public void setContel(ContelVO contel) {
		this.contel = contel;
	}

	
	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}


	


	@Override
	public String toString() {
		return "EventoVO [id=" + id + ", dataHoraInicio=" + dataHoraInicio + ", dataHoraFim=" + dataHoraFim + ", local="
				+ local + ", participantes=" + participantes + ", status=" + status + ", contat=" + contat + ", contel="
				+ contel + ", tipoServico=" + tipoServico + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(contat, contel, dataHoraFim, dataHoraInicio, id, local, participantes, status, tipoServico);
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
		return Objects.equals(contat, other.contat) && Objects.equals(contel, other.contel)
				&& Objects.equals(dataHoraFim, other.dataHoraFim)
				&& Objects.equals(dataHoraInicio, other.dataHoraInicio) && Objects.equals(id, other.id)
				&& Objects.equals(local, other.local) && Objects.equals(participantes, other.participantes)
				&& Objects.equals(status, other.status) && Objects.equals(tipoServico, other.tipoServico);
	}

	
	

} 
