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
import br.com.bancoamazonia.sigh.model.Gestor;

@ManagedBean
@SessionScoped
public class GestorController {
	private Gestor gestor = new Gestor();

	public GestorController() {
	}

	public GestorController(Gestor gestor) {
		super();
		this.gestor = gestor;
	}

	public List<Gestor> getTodas() {
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Gestor> query = manager.createQuery(
					"from Gestor g order by g.sigla", Gestor.class);
			return query.getResultList();
		} finally {
			manager.close();
		}

	}

	// persistindo na base de dados
	public void adicionar() {
		try {
			System.out.println("Estou aqui no gestor->"
					+ this.gestor.getSigla());

			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();

			manager.persist(this.gestor);
			transaction.commit();
			manager.close();

			this.gestor = new Gestor();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Gestor cadastrado com sucesso!", null));

		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
		}

	}

	// MUDAR DE PAGINA
	public String editar() {
		return "editGestor";
	}
	
	// metodo para cancelas - retornar a pagina
	public String cancela(){
		this.gestor = new Gestor();
		return "gestor";
	}
	
	// METODO UPDATE
		public String update() {
			try {
			
					EntityManager manager = JpaUtil.getEntityManager();
					EntityTransaction transaction = manager.getTransaction();
					transaction.begin();

					manager.merge(this.gestor);
					transaction.commit();
					manager.close();
					this.gestor = new Gestor();
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									"Gestor atualizado com sucesso!", null));
				

			} catch (PersistenceException e) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, e
								.getMessage(), null));
			}

			return "gestor";
		}

	// METODO REMOVER
	public String remove() {
		try {

			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			Object o = manager.merge(gestor);
			transaction.begin();
			manager.remove(o);
			transaction.commit();
			manager.close();
			gestor = new Gestor();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Gestor removido com sucesso!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
		}

		return "gestor";
	}

	public Gestor getGestor() {
		return gestor;
	}

	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}

}
