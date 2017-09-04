package br.com.bancoamazonia.sigh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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
import br.com.bancoamazonia.sigh.model.Analista;
import br.com.bancoamazonia.sigh.model.Empresa;
import br.com.bancoamazonia.sigh.model.Gestor;
import br.com.bancoamazonia.sigh.model.Homologacao;
import br.com.bancoamazonia.sigh.model.Homologacao_Sistemas;
import br.com.bancoamazonia.sigh.model.Sdm;
import br.com.bancoamazonia.sigh.model.Sistema;
import br.com.bancoamazonia.sigh.model.Situacao_Homologacao_Sistema;

@ManagedBean
@SessionScoped
public class HomologacaoSistemaController {
	private Homologacao_Sistemas hsistemas = new Homologacao_Sistemas();
	private List<Sistema> sistemas = new ArrayList<Sistema>();
	public List<Homologacao_Sistemas> hmls;
	private List<Homologacao_Sistemas> tamanho; // pega o tamanho da lista do
												// data table
	private List<SelectItem> situacoes;
	private List<Homologacao_Sistemas> selectedHmls;
	private List<Analista> analistas = new ArrayList<Analista>();
	private List<Gestor> gestores = new ArrayList<Gestor>();
	private List<Situacao_Homologacao_Sistema> situacao = new ArrayList<Situacao_Homologacao_Sistema>();
	private Date dataInicial;
	private Date dataFinal;
	// persistindo na base de dados
	@Inject
	UserTransaction userTransaction;

	public HomologacaoSistemaController() {
	}

	public HomologacaoSistemaController(Homologacao_Sistemas hsistemas) {
		super();
		this.hsistemas = hsistemas;
	}

	public Homologacao_Sistemas getHsistemas() {
		return hsistemas;
	}

	public void setHsistemas(Homologacao_Sistemas hsistemas) {
		this.hsistemas = hsistemas;
	}

	// método para limpar formulario
	public void clear() {
		this.dataInicial = null;
		this.dataFinal = null;
		this.hsistemas = new Homologacao_Sistemas();
	}

	public String adicionar() throws NotSupportedException, SystemException, SecurityException, IllegalStateException,
			RollbackException, HeuristicMixedException, HeuristicRollbackException, NamingException {
		try {
			Context context = new InitialContext();
			userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
			EntityManager manager = JpaUtil.getEntityManager();
			Date data = new Date();
			this.hsistemas.setDataInicio(data);
			userTransaction.begin();
			manager.persist(this.hsistemas);

			userTransaction.commit();
			manager.close();

			this.hsistemas = new Homologacao_Sistemas();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro cadastrado com sucesso!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		return "homologacaoSistema";
	}

	// lista datatable
	public List<Homologacao_Sistemas> getTodas() {
		EntityManager manager = JpaUtil.getEntityManager();
		try {

			TypedQuery<Homologacao_Sistemas> query = manager
					.createQuery("from Homologacao_Sistemas h order by h.dataInicio desc", Homologacao_Sistemas.class);
			this.tamanho = query.getResultList();
			return query.getResultList();

		} finally {
			manager.close();
		}

	}

	// METODO UPDATE
	public String update() throws NamingException, NotSupportedException, SystemException, SecurityException,
			IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		try {
			Context context = new InitialContext();
			userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
			EntityManager manager = JpaUtil.getEntityManager();

			userTransaction.begin();

			manager.merge(this.hsistemas);
			userTransaction.commit();
			manager.close();
			this.hsistemas = new Homologacao_Sistemas();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro atualizado com sucesso!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

		return "homologacaoSistema";
	}

	// METODO REMOVER
	public String remove() throws NamingException, NotSupportedException, SystemException, SecurityException,
			IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		try {
			Context context = new InitialContext();
			userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
			EntityManager manager = JpaUtil.getEntityManager();
			Object o = manager.merge(this.hsistemas);
			userTransaction.begin();
			manager.remove(o);
			userTransaction.commit();
			manager.close();
			this.hsistemas = new Homologacao_Sistemas();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro removido com sucesso!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

		return "homologacaoSistema";
	}

	// MUDAR DE PAGINA
	public String editar() {
		return "editHomologacaoSistema";
	}

	// mudar de pagina ao clicar em cancelar
	public String cancela() {
		this.hsistemas = new Homologacao_Sistemas();
		return "homologacaoSistema";
	}

	// IR PARA PAGINA CADASTRAR HOMOLOGAÇÃO
	public String cadastrar() {
		this.hsistemas = new Homologacao_Sistemas();

		return "cadHomologacaoSistema";
	}

	// lista de sistemas no selectone
	public List<SelectItem> getSistemas() {
		List<SelectItem> items = new ArrayList<SelectItem>();

		EntityManager manager = JpaUtil.getEntityManager();

		try {
			TypedQuery<Sistema> query = manager.createQuery("from Sistema", Sistema.class);
			this.sistemas = query.getResultList();
			for (Sistema s : this.sistemas) {
				items.add(new SelectItem(s, s.getNome()));
			}
			return items;
		} finally {
			manager.close();
		}
	}

	// lista de analistas no selectone passando o modulo como parâmetro
	public List<SelectItem> getAnalistas() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		EntityManager manager = JpaUtil.getEntityManager();

		try {

			TypedQuery<Analista> query = manager.createQuery("from Analista a order by a.nome", Analista.class);

			this.analistas = query.getResultList();

			for (Analista a : this.analistas) {
				items.add(new SelectItem(a, a.getNome()));
			}

			return items;
		} finally {
			manager.close();
		}
	}

	// lista de gestor no selectone passando o modulo como parâmetro
	public List<SelectItem> getGestores() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		EntityManager manager = JpaUtil.getEntityManager();

		try {

			TypedQuery<Gestor> query = manager.createQuery("from Gestor g order by g.sigla", Gestor.class);

			this.gestores = query.getResultList();

			for (Gestor g : this.gestores) {
				items.add(new SelectItem(g, g.getSigla()));
			}

			return items;
		} finally {
			manager.close();
		}
	}
	
	public String validaVinculacao(){
		
		if(this.selectedHmls != null){
			for(int i =0; i<this.selectedHmls.size(); i++){
				
				if(this.selectedHmls.get(i).getSdm() != null){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Não é possível vincular chamados que foram a produção em outra(s) SDM´s", null));
					return null;
				}
				
			}
		}
		if(this.selectedHmls.size()==0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"É necessário selecionar chamado(s) para vinculaçao!", null));
			return null;
		}
		return "vinculaSdm";
	}

	// lista de gestor no selectone passando o modulo como parâmetro
	public List<SelectItem> getSituacao() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		EntityManager manager = JpaUtil.getEntityManager();

		try {

			TypedQuery<Situacao_Homologacao_Sistema> query = manager.createQuery(
					"from Situacao_Homologacao_Sistema s order by s.descricao", Situacao_Homologacao_Sistema.class);

			this.situacao = query.getResultList();

			for (Situacao_Homologacao_Sistema s : this.situacao) {
				items.add(new SelectItem(s, s.getDescricao()));
			}

			return items;
		} finally {
			manager.close();
		}
	}

	// lista de gestor no selectone passando o modulo como parâmetro
	public List<SelectItem> getSituacoes() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		EntityManager manager = JpaUtil.getEntityManager();

		try {

			TypedQuery<Situacao_Homologacao_Sistema> query = manager.createQuery(
					"from Situacao_Homologacao_Sistema s order by s.descricao", Situacao_Homologacao_Sistema.class);

			this.situacao = query.getResultList();

			for (Situacao_Homologacao_Sistema s : this.situacao) {
				items.add(new SelectItem(s, s.getDescricao()));
			}

			return items;
		} finally {
			manager.close();
		}
	}

	public void setSituacoes(List<SelectItem> situacoes) {
		this.situacoes = situacoes;
	}

	public boolean filterByChamado(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		if (value == null) {
			return false;
		}
		// return false;

		return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
	}

	// método que altera a situação das funcionalidades a serem homologadas
	public String altera() {
		if (this.selectedHmls != null) {
			for (int i = 0; i < this.selectedHmls.size(); i++) {

				this.hsistemas.setIdHomologaoSistemas(this.selectedHmls.get(i).getIdHomologaoSistemas());

				try {
					EntityManager manager = JpaUtil.getEntityManager();
					EntityTransaction transaction = manager.getTransaction();
					transaction.begin();

					Query q = manager.createQuery(
							"update Homologacao_Sistemas set id_situacao_homologacao_sistema=:idSituacao  Where idHomologaoSistemas = :idHomologacao");
					q.setParameter("idSituacao", this.hsistemas.getSituacoes().getIdSituacaoHomologacaoSistema());
					q.setParameter("idHomologacao", this.hsistemas.getIdHomologaoSistemas());
					// manager.remove(o);
					q.executeUpdate();
					transaction.commit();
					manager.close();
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Alteração da situação realizada com sucesso!", null));
				} catch (PersistenceException e) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
				}
			} // fim do for

		}
		this.hsistemas = new Homologacao_Sistemas();
		this.selectedHmls = new ArrayList<Homologacao_Sistemas>();
		return "homologacaoSistema";
	}

	// metodo do auto complete que sugere, auto completa, sdms
	public List<Sdm> sugerirSdm(String query) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Sdm> q = manager.createQuery("from Sdm s where s.numero = :numero", Sdm.class);
			q.setParameter("numero", Integer.parseInt(query));
			return q.getResultList();
		} finally {
			manager.close();
		}

	}

	// método que altera(gera) sdm p/ registros
	public String geraSdm() throws NamingException {
		if (this.selectedHmls != null) {
			for (int i = 0; i < this.selectedHmls.size(); i++) {

				this.hsistemas.setIdHomologaoSistemas(this.selectedHmls.get(i).getIdHomologaoSistemas());

				try {
					Context context = new InitialContext();
					userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
					EntityManager manager = JpaUtil.getEntityManager();
					userTransaction.begin();
					
					Query q = manager.createQuery(
							"update Homologacao_Sistemas set id_sdm=:idSdm, id_sit_hml_sistema=4  Where idHomologaoSistemas = :idHomologacao");
					q.setParameter("idSdm", this.hsistemas.getSdm().getId_sdm());
					q.setParameter("idHomologacao", this.hsistemas.getIdHomologaoSistemas());
					// manager.remove(o);
					q.executeUpdate();
					userTransaction.commit();
					manager.close();

					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Sdm gerada para os registros selecionados com sucesso!", null));
				} catch (PersistenceException e) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
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
			this.selectedHmls = new ArrayList<Homologacao_Sistemas>();
			this.hsistemas = new Homologacao_Sistemas();
		}

		this.hsistemas = new Homologacao_Sistemas();
		this.selectedHmls = new ArrayList<Homologacao_Sistemas>();
		return "homologacaoSistema";
	}

	public List<Homologacao_Sistemas> getHmls() {
		return hmls;
	}

	public void setHmls(List<Homologacao_Sistemas> hmls) {
		this.hmls = hmls;
	}

	public int getTamanho() {
		return this.tamanho.size();
	}

	public void setTamanho(List<Homologacao_Sistemas> tamanho) {
		this.tamanho = tamanho;
	}

	public List<Homologacao_Sistemas> getSelectedHmls() {
		return selectedHmls;
	}

	public void setSelectedHmls(List<Homologacao_Sistemas> selectedHmls) {
		this.selectedHmls = selectedHmls;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	
}
