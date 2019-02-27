package br.com.bancoamazonia.sigh.model;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "empresa")
public class Empresa {
	private Long idEmpresa;
	private int siscad;
	private String cnpj;
	private String nome;
	private int conta;
	private String nomeContato;
	private String telefone;
	private String email;
	private String nomeAnalista;
	private String contatoAnalista;
	private String emailAnalista;
	private String nomeResponsavel;
	private String contatoResponsavel;
	private String emailResponsavel;
	private String nomeRespAgencia;
	private String telefoneRespAgencia;
	private String emailRespAgencia;
	

	public Empresa() {}
	public Empresa(Long idEmpresa, int siscad, String cnpj, String nome, int conta, String nomeContato, String telefone,
			String email, String nomeAnalista, String contatoAnalista, String emailAnalista, String nomeResponsavel,
			String contatoResponsavel, String emailResponsavel, String nomeRespAgencia, String telefoneRespAgencia,
			String emailRespAgencia) {
		super();
		this.idEmpresa = idEmpresa;
		this.siscad = siscad;
		this.cnpj = cnpj;
		this.nome = nome;
		this.conta = conta;
		this.nomeContato = nomeContato;
		this.telefone = telefone;
		this.email = email;
		this.nomeAnalista = nomeAnalista;
		this.contatoAnalista = contatoAnalista;
		this.emailAnalista = emailAnalista;
		this.nomeResponsavel = nomeResponsavel;
		this.contatoResponsavel = contatoResponsavel;
		this.emailResponsavel = emailResponsavel;
		this.nomeRespAgencia = nomeRespAgencia;
		this.telefoneRespAgencia = telefoneRespAgencia;
		this.emailRespAgencia = emailRespAgencia;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequence_empresa")
	@SequenceGenerator(name="sequence_empresa", sequenceName="sequence_emp", allocationSize=1, initialValue=112)
	@Column(name = "id_empresa")
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	
	@Column(nullable = false)
	public int getSiscad() {
		return siscad;
	}
	public void setSiscad(int siscad) {
		this.siscad = siscad;
	}
	@Column(length = 100, nullable = true)
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	@Column(length = 100, nullable = true)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Column(nullable = true)
	public int getConta() {
		return conta;
	}
	public void setConta(int conta) {
		this.conta = conta;
	}
	@Column(length = 100, nullable = true)
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	@Column(length = 100, nullable = true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="nome_contato", length=100)
	public String getNomeContato() {
		return nomeContato;
	}
	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}
	@Column(name="nome_analista", length=100)
	public String getNomeAnalista() {
		return nomeAnalista;
	}
	public void setNomeAnalista(String nomeAnalista) {
		this.nomeAnalista = nomeAnalista;
	}
	@Column(name="contato_analista", length=100)
	public String getContatoAnalista() {
		return contatoAnalista;
	}
	public void setContatoAnalista(String contatoAnalista) {
		this.contatoAnalista = contatoAnalista;
	}
	@Column(name="email_analista", length=100)
	public String getEmailAnalista() {
		return emailAnalista;
	}
	public void setEmailAnalista(String emailAnalista) {
		this.emailAnalista = emailAnalista;
	}
	@Column(name="nome_responsavel", length=100)
	public String getNomeResponsavel() {
		return nomeResponsavel;
	}
	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}
	@Column(name="contato_responsavel", length=100)
	public String getContatoResponsavel() {
		return contatoResponsavel;
	}
	public void setContatoResponsavel(String contatoResponsavel) {
		this.contatoResponsavel = contatoResponsavel;
	}
	@Column(name="email_responsavel", length=100)
	public String getEmailResponsavel() {
		return emailResponsavel;
	}
	public void setEmailResponsavel(String emailResponsavel) {
		this.emailResponsavel = emailResponsavel;
	}
	@Column(name="nome_resp_agencia", length=100)
	public String getNomeRespAgencia() {
		return nomeRespAgencia;
	}
	public void setNomeRespAgencia(String nomeRespAgencia) {
		this.nomeRespAgencia = nomeRespAgencia;
	}
	@Column(name="telefone_resp_agencia", length=100)
	public String getTelefoneRespAgencia() {
		return telefoneRespAgencia;
	}
	public void setTelefoneRespAgencia(String telefoneRespAgencia) {
		this.telefoneRespAgencia = telefoneRespAgencia;
	}
	@Column(name="email_resp_agencia", length=100)
	public String getEmailRespAgencia() {
		return emailRespAgencia;
	}
	public void setEmailRespAgencia(String emailRespAgencia) {
		this.emailRespAgencia = emailRespAgencia;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idEmpresa == null) ? 0 : idEmpresa.hashCode());
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
		Empresa other = (Empresa) obj;
		if (idEmpresa == null) {
			if (other.idEmpresa != null)
				return false;
		} else if (!idEmpresa.equals(other.idEmpresa))
			return false;
		return true;
	}
	
	
	
}
