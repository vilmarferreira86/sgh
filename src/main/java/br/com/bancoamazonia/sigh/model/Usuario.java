package br.com.bancoamazonia.sigh.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "username", length=40)
	private String username;
	@Column(name = "passwords", length = 100)
	private String password;
	@Column(name = "enables", columnDefinition = "BOOLEAN")
	private boolean enable;
	@ManyToMany
	@JoinTable(name = "user_auth", joinColumns = @JoinColumn(name = "user_username"), inverseJoinColumns = @JoinColumn(name = "auth_authority"))
	private List<Authority> authorities;
	@OneToMany(mappedBy = "usuario", cascade=CascadeType.ALL)
	private List<Hml_Situacao> hmlSituacoes;
	
	
	// getters e setters
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public List<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	public List<Hml_Situacao> getHmlSituacoes() {
		return hmlSituacoes;
	}
	public void setHmlSituacoes(List<Hml_Situacao> hmlSituacoes) {
		this.hmlSituacoes = hmlSituacoes;
	}
	
	

}
