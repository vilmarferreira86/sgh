package br.com.bancoamazonia.sigh.controller;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import br.com.bancoamazonia.sigh.controller.JpaUtil;
import br.com.bancoamazonia.sigh.model.Homologacao_Sistemas;
import br.com.bancoamazonia.sigh.model.Sdm;

@ManagedBean
@SessionScoped
public class SdmController {
	private Sdm sdm = new Sdm();
	private Homologacao_Sistemas hsistemas = new Homologacao_Sistemas();
	private boolean bottomUdt = false; // regra para mostrar botões cancelar e
										// alterar na view homologacaoSistemas,
										// aba sdm
	private boolean bottomGerar = true; // regra para mostrar botão gerar na
										// view homologacaoSistemas, aba sdm
	private List<Sdm> selectedSdms; // lista de sdm selecionadas no dataTable
	public List<Sdm> sdms;
	private List<Homologacao_Sistemas> chamados;
	@Inject
	UserTransaction userTransaction;

	public SdmController() {
	}

	public SdmController(Sdm sdm) {
		super();
		this.sdm = sdm;
	}
	
	public List<Homologacao_Sistemas> getChamados(){
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			
			TypedQuery<Homologacao_Sistemas> query = manager.createQuery("from Homologacao_Sistemas h where h.sdm.id_sdm=:id_sdm", Homologacao_Sistemas.class);
			query.setParameter("id_sdm", this.sdm.getId_sdm());
			return query.getResultList();

		} finally {

			manager.close();
		}
	}

	public String adicionar() {
		try {
			Context context = new InitialContext();
			userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
			EntityManager manager = JpaUtil.getEntityManager();
			
			userTransaction.begin();
			// sdm.setDataInicio(new Date());

			manager.persist(sdm);
			userTransaction.commit();
			manager.close();

			this.sdm = new Sdm();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Sdm Gerada com sucesso! Faça a vinculação dos chamados!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
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

		return "homologacaoSistema";

	}

	// METODO REMOVER
	public void remove() {
		try {
			Context context = new InitialContext();
			userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
			EntityManager manager = JpaUtil.getEntityManager();
			Object o = manager.merge(sdm);
			userTransaction.begin();
			manager.remove(o);
			userTransaction.commit();
			manager.close();
			sdm = new Sdm();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sdm removida com sucesso!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
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

	}

	// METODO UPDATE
	public String update() {
		try {
			Context context = new InitialContext();
			userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
			EntityManager manager = JpaUtil.getEntityManager();
			
			userTransaction.begin();

			manager.merge(this.sdm);
			userTransaction.commit();
			manager.close();
			this.sdm = new Sdm();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sdm atualizada com sucesso!", null));
		} catch (PersistenceException e) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
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
		return "homologacaoSistema";

		// return "analista";
	}

	// método de condição para mostrar os botoes alterar e cancelar na view
	// homologacaoSistemas, aba sdm
	public String editar() {
		return "editSdm";
	}

	// método de condição para cancelar a edição e sumir os botões na view
	// homologacaoSistemas, aba sdm
	public String cancelar() {
		
		this.sdm = new Sdm();
		return "homologacaoSistema";
	}

	// lista datatable
	public List<Sdm> getTodas() {
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Sdm> query = manager.createQuery("from Sdm", Sdm.class);
			return query.getResultList();

		} finally {

			manager.close();
		}

	}

	// método que executa sdm (altera data de execução)
	public String executaSdm() {
		if (this.selectedSdms != null) {
			for (int i = 0; i < this.selectedSdms.size(); i++) {

				this.sdm.setId_sdm(this.selectedSdms.get(i).getId_sdm());

				try {
					EntityManager manager = JpaUtil.getEntityManager();
					Context context = new InitialContext();
					userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
					userTransaction.begin();
					this.sdm.setDataExc(new Date());
					Query q = manager.createQuery("update Sdm set dataExc=:dataExec  Where id_sdm = :id_sdm");
					q.setParameter("dataExec", this.sdm.getDataExc());
					q.setParameter("id_sdm", this.sdm.getId_sdm());
					// manager.remove(o);
					q.executeUpdate();
					userTransaction.commit();
					manager.close();

					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Sdm Executada para os registros selecionados com sucesso!", null));
				} catch (PersistenceException e) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
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
			} // fim do for

		} else {
			this.selectedSdms = new ArrayList<Sdm>();
			this.sdm = new Sdm();
		}

		this.sdm = new Sdm();
		this.selectedSdms = new ArrayList<Sdm>();
		return "homologacaoSistema";
	}

	public Sdm getSdm() {
		return sdm;
	}

	public void setSdm(Sdm sdm) {
		this.sdm = sdm;
	}

	public boolean isBottomUdt() {
		return bottomUdt;
	}

	public void setBottomUdt(boolean bottomUdt) {
		this.bottomUdt = bottomUdt;
	}

	public boolean isBottomGerar() {
		return bottomGerar;
	}

	public void setBottomGerar(boolean bottomGerar) {
		this.bottomGerar = bottomGerar;
	}

	public List<Sdm> getSelectedSdms() {
		return selectedSdms;
	}

	public void setSelectedSdms(List<Sdm> selectedSdms) {
		this.selectedSdms = selectedSdms;
	}

	public List<Sdm> getSdms() {
		return sdms;
	}

	public void setSdms(List<Sdm> sdms) {
		this.sdms = sdms;
	}

}
