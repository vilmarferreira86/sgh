package br.com.bancoamazonia.sigh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "analista")
public class Analista {
	private Long idAnalista;
	private String nome;
	private String cargo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequence_analista")
	@SequenceGenerator(name="sequence_analista", sequenceName="sequence_anl", allocationSize=1, initialValue=9)
	@Column(name = "id_analista")
	public Long getIdAnalista() {
		return idAnalista;
	}
	public void setIdAnalista(Long idAnalista) {
		this.idAnalista = idAnalista;
	}
	@Column(length = 100, nullable = false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Column(length = 100, nullable = true)
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idAnalista == null) ? 0 : idAnalista.hashCode());
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
		Analista other = (Analista) obj;
		if (idAnalista == null) {
			if (other.idAnalista != null)
				return false;
		} else if (!idAnalista.equals(other.idAnalista))
			return false;
		return true;
	}
	
	

}
