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
import br.com.bancoamazonia.sigh.model.Agencia;
import br.com.bancoamazonia.sigh.model.Situacao;

@ManagedBean
@SessionScoped
public class SituacaoController {
	private Situacao situacao = new Situacao();

	public SituacaoController() {
	}

	public SituacaoController(Situacao situacao) {
		super();
		this.situacao = situacao;
	}

	// persistindo na base de dados
	public void adicionar() {
		try {
			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();

			manager.persist(situacao);
			transaction.commit();
			manager.close();

			this.situacao = new Situacao();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Situação cadastrado com sucesso!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							e.getMessage(),
							null));
		}
		// return "agencia";
	}

	// lista datatable
	public List<Situacao> getTodas() {
		EntityManager manager = JpaUtil.getEntityManager();
		try{
			TypedQuery<Situacao> query = manager.createQuery("from Situacao",
					Situacao.class);
			return query.getResultList();
		}finally{
			manager.close();
		}
		
	}

	// METODO UPDATE
	public String update() {
		try {
			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();

			manager.merge(situacao);
			transaction.commit();
			manager.close();
			this.situacao = new Situacao();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Situação atualizada com sucesso!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
		}

		return "situacao";
	}

	// METODO REMOVER
	public String remove() {
		try {

			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			Object o = manager.merge(situacao);
			transaction.begin();
			manager.remove(o);
			transaction.commit();
			manager.close();
			situacao = new Situacao();
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

		return "situacao";
	}

	// MUDAR DE PAGINA
	public String editar() {
		return "editSituacao";
	}
	
	public String cancela(){
		this.situacao = new Situacao();
		return "situacao";
	}

	// GETTERS AND SETTERS
	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

}
