package br.com.bancoamazonia.sigh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "gestor")
public class Gestor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idGestor;
	private String sigla;
	private String descricao;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequence_gestor")
	@SequenceGenerator(name="sequence_gestor", sequenceName="sequence_gstr", allocationSize=1, initialValue=12)
	@Column(name = "id_gestor")
	public Long getIdGestor() {
		return idGestor;
	}
	public void setIdGestor(Long idGestor) {
		this.idGestor = idGestor;
	}
	
	@Column(length = 20, nullable = false)
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	@Column(length = 100, nullable = false)
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idGestor == null) ? 0 : idGestor.hashCode());
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
		Gestor other = (Gestor) obj;
		if (idGestor == null) {
			if (other.idGestor != null)
				return false;
		} else if (!idGestor.equals(other.idGestor))
			return false;
		return true;
	}
	
	
	
}
