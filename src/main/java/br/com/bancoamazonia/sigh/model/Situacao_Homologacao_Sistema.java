package br.com.bancoamazonia.sigh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author 7639
 *
 */
@Entity
@Table(name = "situacao_homologacao_sistema")
public class Situacao_Homologacao_Sistema implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idSituacaoHomologacaoSistema;
	private String descricao;
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "sequence_sit_hml_sistema")
	@SequenceGenerator(name="sequence_sit_hml_sistema", sequenceName="sequence_sit_hml_sistema", allocationSize=1, initialValue=8)
	@Column(name = "id_sit_hml_sistema")
	public Long getIdSituacaoHomologacaoSistema() {
		return idSituacaoHomologacaoSistema;
	}
	public void setIdSituacaoHomologacaoSistema(Long idSituacaoHomologacaoSistema) {
		this.idSituacaoHomologacaoSistema = idSituacaoHomologacaoSistema;
	}
	@Column(name="descricao", length=255, nullable=false)
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
		result = prime
				* result
				+ ((idSituacaoHomologacaoSistema == null) ? 0
						: idSituacaoHomologacaoSistema.hashCode());
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
		Situacao_Homologacao_Sistema other = (Situacao_Homologacao_Sistema) obj;
		if (idSituacaoHomologacaoSistema == null) {
			if (other.idSituacaoHomologacaoSistema != null)
				return false;
		} else if (!idSituacaoHomologacaoSistema
				.equals(other.idSituacaoHomologacaoSistema))
			return false;
		return true;
	}
	
	

}
