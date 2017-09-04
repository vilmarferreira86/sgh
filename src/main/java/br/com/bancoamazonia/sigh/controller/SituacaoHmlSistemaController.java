package br.com.bancoamazonia.sigh.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.bancoamazonia.sigh.controller.JpaUtil;
import br.com.bancoamazonia.sigh.model.Situacao_Homologacao_Sistema;

@ManagedBean
@SessionScoped
public class SituacaoHmlSistemaController {
	private Situacao_Homologacao_Sistema situacao = new Situacao_Homologacao_Sistema();

	public SituacaoHmlSistemaController() {

	}

	public SituacaoHmlSistemaController(Situacao_Homologacao_Sistema situacoes) {
		super();
		this.situacao = situacoes;
	}

	public List<Situacao_Homologacao_Sistema> getTodas() {
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Situacao_Homologacao_Sistema> query = manager
					.createQuery(
							"from Situacao_Homologacao_Sistema s order by s.descricao",
							Situacao_Homologacao_Sistema.class);
			return query.getResultList();
		} finally {
			manager.close();
		}

	}

	// persistindo na base de dados
	public void adicionar() {
		try {

			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();

			manager.persist(this.situacao);
			transaction.commit();
			manager.close();

			this.situacao = new Situacao_Homologacao_Sistema();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Situação cadastrado com sucesso!", null));

		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
		}

	}

	// MUDAR DE PAGINA
	public String editar() {
		return "editSituacaohmlsistema";
	}

	// metodo para cancelas - retornar a pagina
	public String cancela() {
		this.situacao = new Situacao_Homologacao_Sistema();
		return "situacaohmlsistemas";
	}

	// METODO UPDATE
	public String update() {
		try {

			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();

			manager.merge(this.situacao);
			transaction.commit();
			manager.close();
			this.situacao = new Situacao_Homologacao_Sistema();
			
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Situacao atualizada com sucesso!", null));
			return "situacaohmlsistemas";

		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
		}

		return "situacaohmlsistemas";
	}

	// METODO REMOVER
	public String remove() {
		try {

			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			Object o = manager.merge(this.situacao);
			transaction.begin();
			manager.remove(o);
			transaction.commit();
			manager.close();
			this.situacao = new Situacao_Homologacao_Sistema();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Situação removida com sucesso!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
		}

		return "situacaohmlsistema";
	}

	public Situacao_Homologacao_Sistema getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao_Homologacao_Sistema situacao) {
		this.situacao = situacao;
	}

}
