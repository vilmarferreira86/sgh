package br.com.bancoamazonia.sigh.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoamazonia.sigh.controller.JpaUtil;
import br.com.bancoamazonia.sigh.model.Agencia;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

@ManagedBean
@SessionScoped

public class AgenciaController {
	private Agencia agencia = new Agencia();
	private List<Agencia> agencias;
	private JpaUtil util;

	public AgenciaController() {

	}

	public AgenciaController(Agencia agencias) {
		super();
		this.agencia = agencias;
	}

	// persistindo na base de dados
	public void adicionar() {
		try {

			if (agencia.getNumero() == 0) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_ERROR,
										"Não pode ser cadastrado agência com número igual a 0!!",
										null));
			} else {
				EntityManager manager = JpaUtil.getEntityManager();
				EntityTransaction transaction = manager.getTransaction();
				transaction.begin();

				System.out.println("numero agencia ->" + agencia.getNumero());
				manager.persist(agencia);
				transaction.commit();
				manager.close();

				this.agencia = new Agencia();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Agência cadastrada com sucesso!", null));
			}

		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							e.getMessage() +
							"Já existe agência cadastrada com este número!!",
							null));
		}
		// return "agencia";
	}

	// METODO UPDATE
	public String update() {
		try {
			if (agencia.getNumero() == 0) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_ERROR,
										"Não pode ser cadastrado agência com número igual a 0!!",
										null));
			} else {
				System.out.println("Numero agencia ->" + agencia.getNumero());
				EntityManager manager = JpaUtil.getEntityManager();
				EntityTransaction transaction = manager.getTransaction();
				transaction.begin();

				manager.merge(agencia);
				transaction.commit();
				manager.close();
				this.agencia = new Agencia();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Agência atualizada com sucesso!", null));
			}

		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
		}

		return "agencia";
	}

	// METODO REMOVER
	public String remove() {
		try {
			System.out.println("Numero agencia ->" + agencia.getNumero());

			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			Object o = manager.merge(agencia);
			transaction.begin();
			manager.remove(o);
			transaction.commit();
			manager.close();
			agencia = new Agencia();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Agência removida com sucesso!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
		}

		return "agencia";
	}

	// MUDAR DE PAGINA
	public String editar() {
		return "editAgencia";
	}
	
	public String cancela(){
		this.agencia = new Agencia();
		return "agencia";
	}
	
	public List<Agencia> getTodas() {
		EntityManager manager = JpaUtil.getEntityManager();
		try{
			TypedQuery<Agencia> query = manager.createQuery("from Agencia a order by a.numero, a.nome",
					Agencia.class);
			return query.getResultList();
		}finally{
			manager.close();
		}
		
	}
	
	public List<SelectItem> getAgencies(){
		List<SelectItem> items = new ArrayList<SelectItem>();
		EntityManager manager = JpaUtil.getEntityManager();
		try{
			TypedQuery<Agencia> q = manager.createQuery("FROM Agencia a WHERE a.numero > 7000 order by a.nome", Agencia.class);
			agencias = q.getResultList();
			for(Agencia a : this.agencias){
				items.add(new SelectItem(a, a.getNumero()+ "-" + a.getNome()));
			}
			return items;
		}finally{
			manager.close();
		}
	}

	// GETTERS AND SETTERS

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public List<Agencia> getAgencias() {
		return agencias;
	}

	public void setAgencias(List<Agencia> agencias) {
		this.agencias = agencias;
	}

	public JpaUtil getUtil() {
		return util;
	}

	public void setUtil(JpaUtil util) {
		this.util = util;
	}
	

}
