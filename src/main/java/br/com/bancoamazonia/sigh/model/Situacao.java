package br.com.bancoamazonia.sigh.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "situacao")
public class Situacao {
	private Long idSituacao;
	private String descricao;
	private List<Hml_Situacao> hmlSituacoes;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_situacao")
	@SequenceGenerator(name="sequence_situacao", sequenceName="sequence_situacao", allocationSize=1, initialValue=18)
	@Column(name = "id_situacao")
	public Long getIdSituacao() {
		return idSituacao;
	}
	public void setIdSituacao(Long idSituacao) {
		this.idSituacao = idSituacao;
	}
	
	@Column(length = 100, nullable = false)
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	@OneToMany(mappedBy = "situacao", cascade=CascadeType.ALL)
	public List<Hml_Situacao> getHmlSituacoes() {
		return hmlSituacoes;
	}
	public void setHmlSituacoes(List<Hml_Situacao> hmlSituacoes) {
		this.hmlSituacoes = hmlSituacoes;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idSituacao == null) ? 0 : idSituacao.hashCode());
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
		Situacao other = (Situacao) obj;
		if (idSituacao == null) {
			if (other.idSituacao != null)
				return false;
		} else if (!idSituacao.equals(other.idSituacao))
			return false;
		return true;
	}
	
	
}
