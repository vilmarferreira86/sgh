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
import br.com.bancoamazonia.sigh.model.Analista;
import br.com.bancoamazonia.sigh.model.Empresa;

@ManagedBean
@SessionScoped
public class AnalistaController {
	private Analista analista = new Analista();

	public AnalistaController() {
	}

	public AnalistaController(Analista analista) {
		super();
		this.analista = analista;
	}

	// persistindo na base de dados
	public void adicionar() {
		try {
			System.out.println("nome analista ->" + analista.getNome());
			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();

			
			manager.persist(analista);
			transaction.commit();
			manager.close();

			this.analista = new Analista();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Analista cadastrado com sucesso!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Já existe agência cadastrada com este número!!",
							null));
		}
		// return "agencia";
	}

	// lista datatable
	public List<Analista> getTodas() {
		EntityManager manager = JpaUtil.getEntityManager();
		try{
			TypedQuery<Analista> query = manager.createQuery("from Analista",
					Analista.class);
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

			manager.merge(analista);
			transaction.commit();
			manager.close();
			this.analista = new Analista();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Analista atualizado com sucesso!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
		}

		return "analista";
	}

	// METODO REMOVER
	public String remove() {
		try {

			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			Object o = manager.merge(analista);
			transaction.begin();
			manager.remove(o);
			transaction.commit();
			manager.close();
			analista = new Analista();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Analista removido com sucesso!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
		}

		return "analista";
	}

	// MUDAR DE PAGINA
	public String editar() {
		return "editAnalista";
	}
	
	public String cancela(){
		this.analista = new Analista();
		return "analista";
	}
	
	// GETTERS AND SETTERS
	public Analista getAnalista() {
		return analista;
	}

	public void setAnalista(Analista analista) {
		this.analista = analista;
	}

}
