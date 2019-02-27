package br.com.bancoamazonia.sigh.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

@Entity
@Table(name = "hml_sit")
public class Hml_Situacao {
	private Long idHmlSit;
	
	private String observacao;
	private Date dataAtualizacao;
	private Homologacao homologacao;
	private Situacao situacao;
	private Usuario usuario;
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="sequence_hml_sit")
	@SequenceGenerator(name="sequence_hml_sit", sequenceName="sequence_hml_sit", allocationSize=1, initialValue=880)
	@Column(name = "id_hml_sit")
	public Long getIdHmlSit() {
		return idHmlSit;
	}
	public void setIdHmlSit(Long idHmlSit) {
		this.idHmlSit = idHmlSit;
	}
	
	@ManyToOne
	@JoinColumn(name = "homologacao_id")
	public Homologacao getHomologacao() {
		return homologacao;
	}
	public void setHomologacao(Homologacao homologacao) {
		this.homologacao = homologacao;
	}
	@ManyToOne
	@JoinColumn(name = "situacao_id")
	public Situacao getSituacao() {
		return situacao;
	}
	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	
	
	
	
	@ManyToOne
	@JoinColumn(name = "username")
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Column(name = "observacao", length = 255, nullable=true)
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="data_atualizacao")
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idHmlSit == null) ? 0 : idHmlSit.hashCode());
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
		Hml_Situacao other = (Hml_Situacao) obj;
		if (idHmlSit == null) {
			if (other.idHmlSit != null)
				return false;
		} else if (!idHmlSit.equals(other.idHmlSit))
			return false;
		return true;
	}
	
	

}
