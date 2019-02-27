package br.com.bancoamazonia.sigh.model;

import java.util.ArrayList;
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
@Table(name = "homologacao")
public class Homologacao {
	private Long idHomologacao;
	private Empresa empresa = new Empresa();
	private Agencia agencia = new Agencia();
	private Analista analista = new Analista();
	private int chamado;
	private int convenio;
	private Date dataInicio;
	private String opcao;
	private String situacaoAtual;
	private Date dataAtualizacaoSituacao;
	private int faixaIniHml;
	private int faixaFimHml;
	private int faixaIniProd;
	private int faixaFimProd;
	private String padrao;
	private List<Hml_Situacao> hmlSituacoes;
	private String situacao;

	public Homologacao() {
		// TODO Auto-generated constructor stub
	}

	public Homologacao(Long idHomologacao, String nomeEmpresa, int siscad, String nomeRespAgencia, int numAgencia,
			String nomeAgencia, String nomeAnalista, int chamado, int convenio, Date dataInicio, String opcao,
			String padrao, String situacao) {
		super();
		this.idHomologacao = idHomologacao;
		this.empresa.setNome(nomeEmpresa);
		this.empresa.setSiscad(siscad);
		this.empresa.setNomeRespAgencia(nomeRespAgencia);
		this.agencia.setNumero(numAgencia);
		this.agencia.setNome(nomeAgencia);
		this.analista.setNome(nomeAnalista);
		this.chamado = chamado;
		this.convenio = convenio;
		this.dataInicio = dataInicio;
		this.opcao = opcao;
		this.padrao = padrao;
		this.situacao = situacao;
	}

	public Homologacao(Long idHomologacao, Empresa empresa, Agencia agencia, Analista analista, int chamado,
			int convenio, Date dataInicio, String opcao, String situacaoAtual, Date dataAtualizacaoSituacao,
			int faixaIniHml, int faixaFimHml, int faixaIniProd, int faixaFimProd, String padrao,
			List<Hml_Situacao> hmlSituacoes, String situacao) {
		super();
		this.idHomologacao = idHomologacao;
		this.empresa = empresa;
		this.agencia = agencia;
		this.analista = analista;
		this.chamado = chamado;
		this.convenio = convenio;
		this.dataInicio = dataInicio;
		this.opcao = opcao;
		this.situacaoAtual = situacaoAtual;
		this.dataAtualizacaoSituacao = dataAtualizacaoSituacao;
		this.faixaIniHml = faixaIniHml;
		this.faixaFimHml = faixaFimHml;
		this.faixaIniProd = faixaIniProd;
		this.faixaFimProd = faixaFimProd;
		this.padrao = padrao;
		this.hmlSituacoes = hmlSituacoes;
		this.situacao = situacao;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_hml")
	@SequenceGenerator(name = "sequence_hml", sequenceName = "sequence_hml", allocationSize = 1, initialValue = 136)
	@Column(name = "id_homologacao")
	public Long getIdHomologacao() {
		return idHomologacao;
	}

	public void setIdHomologacao(Long idHomologacao) {
		this.idHomologacao = idHomologacao;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_empresa")
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_analista")
	public Analista getAnalista() {
		return analista;
	}

	public void setAnalista(Analista analista) {
		this.analista = analista;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_agencia")
	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	@Column(nullable = false)
	public int getChamado() {
		return chamado;
	}

	public void setChamado(int chamado) {
		this.chamado = chamado;
	}

	@Column(nullable = false)
	public int getConvenio() {
		return convenio;
	}

	public void setConvenio(int convenio) {
		this.convenio = convenio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicio", nullable = true)
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	@Column(name = "opcao", length = 255, nullable = true)
	public String getOpcao() {
		return opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}

	@Column(name = "situacao_atual", nullable = true)
	public String getSituacaoAtual() {
		return situacaoAtual;
	}

	public void setSituacaoAtual(String situacaoAtual) {
		this.situacaoAtual = situacaoAtual;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_atu_sit_atual", nullable = true)
	public Date getDataAtualizacaoSituacao() {
		return dataAtualizacaoSituacao;
	}

	public void setDataAtualizacaoSituacao(Date dataAtualizacaoSituacao) {
		this.dataAtualizacaoSituacao = dataAtualizacaoSituacao;
	}

	@Column(name = "padrao", nullable = false)
	public String getPadrao() {
		return padrao;
	}

	public void setPadrao(String padrao) {
		this.padrao = padrao;
	}

	@Column(name = "situacao", length = 100, nullable = true)
	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	@Column(name = "faixaIniHml", nullable = true)
	public int getFaixaIniHml() {
		return faixaIniHml;
	}

	public void setFaixaIniHml(int faixaIniHml) {
		this.faixaIniHml = faixaIniHml;
	}

	@Column(name = "faixaFimHml", nullable = true)
	public int getFaixaFimHml() {
		return faixaFimHml;
	}

	public void setFaixaFimHml(int faixaFimHml) {
		this.faixaFimHml = faixaFimHml;
	}

	@Column(name = "faixaIniProd", nullable = true)
	public int getFaixaIniProd() {
		return faixaIniProd;
	}

	public void setFaixaIniProd(int faixaIniProd) {
		this.faixaIniProd = faixaIniProd;
	}

	@Column(name = "faixaFimProd", nullable = true)
	public int getFaixaFimProd() {
		return faixaFimProd;
	}

	public void setFaixaFimProd(int faixaFimProd) {
		this.faixaFimProd = faixaFimProd;
	}

	@OneToMany(mappedBy = "homologacao", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
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
		result = prime * result + ((idHomologacao == null) ? 0 : idHomologacao.hashCode());
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
		Homologacao other = (Homologacao) obj;
		if (idHomologacao == null) {
			if (other.idHomologacao != null)
				return false;
		} else if (!idHomologacao.equals(other.idHomologacao))
			return false;
		return true;
	}

}
