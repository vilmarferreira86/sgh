package br.com.bancoamazonia.sigh.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sdm")
public class Sdm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id_sdm;
	private int numero;
	private String tipo;
	private String titulo;
	private String descricao;
	private String situacao;
	private Date dataInicio;
	private Date dataExc;
	private List<Homologacao_Sistemas> homologacoes;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_sdm")
	@SequenceGenerator(name="sequence_sdm", sequenceName="sequence_sdm", allocationSize=1, initialValue=24)
	@Column(name = "id_sdm")
	public Long getId_sdm() {
		return id_sdm;
	}

	public void setId_sdm(Long id_sdm) {
		this.id_sdm = id_sdm;
	}

	@Column(name = "numero", nullable = false)
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Column(name = "tipo", length = 100)
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Column(name = "titulo", length = 100)
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Column(name = "descricao", length = 1000)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Column(name="situacao", length=100)
	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="datainicio")
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="dataexec", nullable=true)
	public Date getDataExc() {
		return dataExc;
	}

	public void setDataExc(Date dataExc) {
		this.dataExc = dataExc;
	}
	
	
	@OneToMany(mappedBy = "sdm", cascade = CascadeType.ALL)
	public List<Homologacao_Sistemas> getHomologacoes() {
		return homologacoes;
	}

	public void setHomologacoes(List<Homologacao_Sistemas> homologacoes) {
		this.homologacoes = homologacoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_sdm == null) ? 0 : id_sdm.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sdm other = (Sdm) obj;
		if (id_sdm == null) {
			if (other.id_sdm != null)
				return false;
		} else if (!id_sdm.equals(other.id_sdm))
			return false;
		return true;
	}
	
	

}
