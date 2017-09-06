package br.com.bancoamazonia.sigh.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import br.com.bancoamazonia.sigh.controller.JpaUtil;
import br.com.bancoamazonia.sigh.model.Agencia;
import br.com.bancoamazonia.sigh.model.Situacao;

@ManagedBean
@SessionScoped
public class SituacaoController {
	private Situacao situacao = new Situacao();
	@Inject
	UserTransaction userTransaction;
	
	public SituacaoController() {
	}

	public SituacaoController(Situacao situacao) {
		super();
		this.situacao = situacao;
	}

	// persistindo na base de dados
	public void adicionar() {
		try {
			Context context = new InitialContext();
			userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
			EntityManager manager = JpaUtil.getEntityManager();
			//EntityTransaction transaction = manager.getTransaction();
			userTransaction.begin();

			manager.persist(situacao);
			userTransaction.commit();
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
 catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotSupportedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (SystemException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (SecurityException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IllegalStateException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (RollbackException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (HeuristicMixedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (HeuristicRollbackException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
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
			Context context = new InitialContext();
			userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
			EntityManager manager = JpaUtil.getEntityManager();
			//EntityTransaction transaction = manager.getTransaction();
			userTransaction.begin();

			manager.merge(situacao);
			userTransaction.commit();
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
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "situacao";
	}

	// METODO REMOVER
	public String remove() {
		try {
			Context context = new InitialContext();
			userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
			EntityManager manager = JpaUtil.getEntityManager();
			//EntityTransaction transaction = manager.getTransaction();
			Object o = manager.merge(situacao);
			userTransaction.begin();
			manager.remove(o);
			userTransaction.commit();
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
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
