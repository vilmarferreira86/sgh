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
@Table(name = "sistema")       
public class Sistema implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idSistema;
	private String sigla;
	private String nome;
	private String descricao;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sequence_sistema")
	@SequenceGenerator(name="sequence_sistema", sequenceName="sequence_sistema", allocationSize=1, initialValue=4)
	@Column(name = "id_sistema")
	public Long getIdSistema() {
		return idSistema;
	}
	public void setIdSistema(Long idSistema) {
		this.idSistema = idSistema;
	}
	@Column(length = 20, nullable = false)
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	@Column(length = 100, nullable = false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Column(length = 255, nullable = true)
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
				+ ((idSistema == null) ? 0 : idSistema.hashCode());
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
		Sistema other = (Sistema) obj;
		if (idSistema == null) {
			if (other.idSistema != null)
				return false;
		} else if (!idSistema.equals(other.idSistema))
			return false;
		return true;
	}
	
	

}
