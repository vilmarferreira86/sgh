package br.com.bancoamazonia.sigh.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "homologacao_sistemas")
public class Homologacao_Sistemas implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idHomologaoSistemas;
	private int chamado;
	private String descricao;
	private String tipo;
	private int numMantis;
	private Date dataInicio;
	private Analista analistas;
	private Sistema sistemas;
	private Gestor gestores;
	private Situacao_Homologacao_Sistema situacoes;
	private String titulo;
	private Sdm sdm;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_hml_sistemas")
	@SequenceGenerator(name="sequence_hml_sistemas", sequenceName="sequence_hml_sistemas", allocationSize=1, initialValue=92)
	@Column(name = "id_homologacao_sistema")
	public Long getIdHomologaoSistemas() {
		return idHomologaoSistemas;
	}
	public void setIdHomologaoSistemas(Long idHomologaoSistemas) {
		this.idHomologaoSistemas = idHomologaoSistemas;
	}
	@Column(name = "chamado", nullable= false)
	public int getChamado() {
		return chamado;
	}
	public void setChamado(int chamado) {
		this.chamado = chamado;
	}
	@Column(name = "descricao", length=1000, nullable= false)
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_inicio",nullable = true)
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	
	@ManyToOne(optional=false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_analista")
	public Analista getAnalistas() {
		return analistas;
	}
	public void setAnalistas(Analista analistas) {
		this.analistas = analistas;
	}
	@ManyToOne(optional=false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_gestor")
	public Gestor getGestores() {
		return gestores;
	}
	public void setGestores(Gestor gestores) {
		this.gestores = gestores;
	}
	
	
	@ManyToOne(optional=false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_sistema")
	public Sistema getSistemas() {
		return sistemas;
	}
	public void setSistemas(Sistema sistemas) {
		this.sistemas = sistemas;
	}
	@ManyToOne(optional=false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_sit_hml_sistema")
	public Situacao_Homologacao_Sistema getSituacoes() {
		return situacoes;
	}
	public void setSituacoes(Situacao_Homologacao_Sistema situacoes) {
		this.situacoes = situacoes;
	}
	
	
	@Column(name="tipo", length=100, nullable=false)
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Column(name="num_mantis", nullable=false)
	public int getNumMantis() {
		return numMantis;
	}
	public void setNumMantis(int numMantis) {
		this.numMantis = numMantis;
	}
	
	
	@Column(name="titulo", length=100, nullable=true)
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@ManyToOne(optional=true, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_sdm",nullable = true)
	public Sdm getSdm() {
		return sdm;
	}
	public void setSdm(Sdm sdm) {
		this.sdm = sdm;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((idHomologaoSistemas == null) ? 0 : idHomologaoSistemas
						.hashCode());
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
		Homologacao_Sistemas other = (Homologacao_Sistemas) obj;
		if (idHomologaoSistemas == null) {
			if (other.idHomologaoSistemas != null)
				return false;
		} else if (!idHomologaoSistemas.equals(other.idHomologaoSistemas))
			return false;
		return true;
	}
	
	
}
