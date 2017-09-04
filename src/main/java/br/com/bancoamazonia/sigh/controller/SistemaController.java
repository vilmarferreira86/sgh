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
import br.com.bancoamazonia.sigh.model.Sistema;

@ManagedBean
@SessionScoped
public class SistemaController {
	private Sistema sistema = new Sistema();
	
	public SistemaController(){}
	
	public SistemaController(Sistema sistema){
		super();
		this.sistema = sistema;
	}
	
	// persistindo na base de dados
	public void adicionar() {
		try {
			System.out.println("Sistema-> "+this.sistema);
			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();

			manager.persist(sistema);
			transaction.commit();
			manager.close();

			this.sistema = new Sistema();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Sistema cadastrado com sucesso!", null));
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
	public List<Sistema> getTodas() {
		EntityManager manager = JpaUtil.getEntityManager();
		try{
			TypedQuery<Sistema> query = manager.createQuery("from Sistema",
					Sistema.class);
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

			manager.merge(this.sistema);
			transaction.commit();
			manager.close();
			this.sistema = new Sistema();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Sistema atualizado com sucesso!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
		}

		return "sistema";
	}

	// METODO REMOVER
	public String remove() {
		try {

			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			Object o = manager.merge(this.sistema);
			transaction.begin();
			manager.remove(o);
			transaction.commit();
			manager.close();
			this.sistema = new Sistema();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Sistema removido com sucesso!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
		}

		return "sistema";
	}

	// MUDAR DE PAGINA
	public String editar() {
		return "editSistema";
	}
	
	public String cancela(){
		this.sistema = new Sistema();
		return "sistema";
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}
	
	
}
