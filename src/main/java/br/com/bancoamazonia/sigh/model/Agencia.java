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
@Table(name = "agencia")
public class Agencia implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idAgencia;
	private int numero;
	private String nome;
	private int digitoVerificador;
	private String cnpjUnidade;
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="sequence_agencia")
	@SequenceGenerator(name="sequence_agencia", sequenceName="sequence_agn", allocationSize=1, initialValue=136)
	@Column(name = "id_agencia")
	public Long getIdAgencia() {
		return idAgencia;
	}
	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}
	@Column(nullable = false, unique=true)
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	@Column(length = 100, nullable = false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name="digito_verificador", nullable=false)
	public int getDigitoVerificador() {
		return digitoVerificador;
	}
	public void setDigitoVerificador(int digitoVerificador) {
		this.digitoVerificador = digitoVerificador;
	}
	
	@Column(name="cgc_und", length=100)
	public String getCnpjUnidade() {
		return cnpjUnidade;
	}
	public void setCnpjUnidade(String cnpjUnidade) {
		this.cnpjUnidade = cnpjUnidade;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idAgencia == null) ? 0 : idAgencia.hashCode());
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
		Agencia other = (Agencia) obj;
		if (idAgencia == null) {
			if (other.idAgencia != null)
				return false;
		} else if (!idAgencia.equals(other.idAgencia))
			return false;
		return true;
	}
	
	
	
	
	
	
}
