package br.com.bancoamazonia.sigh.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.sound.midi.Patch;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import br.com.bancoamazonia.sigh.controller.JpaUtil;
import br.com.bancoamazonia.sigh.model.Agencia;
import br.com.bancoamazonia.sigh.model.Empresa;
import br.com.bancoamazonia.sigh.model.Homologacao;

@ManagedBean
@SessionScoped
public class EmpresaController {
	private List<Empresa> empresasFiltradas;
	private Empresa empresa = new Empresa();
	private List<Agencia> agencias = new ArrayList<Agencia>();
	
	@Inject
	UserTransaction userTransaction;

	public EmpresaController() {
	}

	public EmpresaController(Empresa empresa) {
		super();
		this.empresa = empresa;
	}

	// persistindo na base de dadosa
	public String adicionar() {
		try {
			if (empresa.getNome().equals("")) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome da empresa é obrigatório!", null));
			} else if (empresa.getSiscad() == 0) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Siscad da empresa é obrigatório!", null));
			}

			else if (empresa.getConta() == 0) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conta da empresa é obrigatória!", null));
			} else {
				Context context = new InitialContext();
				userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
				EntityManager manager = JpaUtil.getEntityManager();
				// EntityTransaction transaction = manager.getTransaction();
				// transaction.begin();
				userTransaction.begin();
				empresa.setNome(empresa.getNome().toUpperCase());

				manager.persist(empresa);
				userTransaction.commit();
				manager.close();

				this.empresa = new Empresa();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Empresa cadastrada com sucesso!", null));
			}

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
		return "empresa";
	}

	// botao para limpar formulario
	public void clear() {
		this.empresa = new Empresa();
	}

	// navegar de empresa para viewEmmpresa
	public String goViewEmpresa() {
		return "viewEmpresa";
	}

	public void add() {
		System.out.println("empresa nome->" + empresa.getNome());
		/*
		 * try { if (empresa.getNome().equals("")) {
		 * FacesContext.getCurrentInstance().addMessage( null, new
		 * FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome da empresa é obrigatório!",
		 * null)); } else if (empresa.getSiscad() == 0){
		 * FacesContext.getCurrentInstance().addMessage( null, new
		 * FacesMessage(FacesMessage.SEVERITY_ERROR, "Siscad da empresa é obrigatório!",
		 * null)); }
		 * 
		 * else if(empresa.getConta() == 0){
		 * FacesContext.getCurrentInstance().addMessage( null, new
		 * FacesMessage(FacesMessage.SEVERITY_ERROR, "Conta da empresa é obrigatória!",
		 * null)); } else { EntityManager manager = JpaUtil.getEntityManager();
		 * EntityTransaction transaction = manager.getTransaction();
		 * transaction.begin(); empresa.setNome(empresa.getNome().toUpperCase());
		 * 
		 * manager.persist(empresa); transaction.commit(); manager.close();
		 * 
		 * this.empresa = new Empresa(); FacesContext.getCurrentInstance().addMessage(
		 * null, new FacesMessage(FacesMessage.SEVERITY_INFO,
		 * "Empresa cadastrada com sucesso!", null)); }
		 * 
		 * } catch (PersistenceException e) {
		 * FacesContext.getCurrentInstance().addMessage( null, new
		 * FacesMessage(FacesMessage.SEVERITY_ERROR, e .getMessage(), null)); }
		 */
	}

	// METODO UPDATE
	public String update() {
		try {
			if (empresa.getNome().equals("")) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome da empresa é obrigatório!", null));
			} else if (empresa.getSiscad() == 0) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Siscad da empresa é obrigatório!", null));
			}

			else if (empresa.getConta() == 0) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conta da empresa é obrigatória!", null));
			} else {
				empresa.setNome(empresa.getNome().toUpperCase());
				Context context = new InitialContext();
				userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
				EntityManager manager = JpaUtil.getEntityManager();
				// EntityManager manager = JpaUtil.getEntityManager();
				// EntityTransaction transaction = manager.getTransaction();
				userTransaction.begin();

				manager.merge(empresa);
				userTransaction.commit();
				manager.close();
				this.empresa = new Empresa();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Empresa atualizada com sucesso!", null));
			}

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

		return "empresa";
	}

	// METODO REMOVER
	public String remove() throws SQLException {
		try {
			Context context = new InitialContext();
			userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
			EntityManager manager = JpaUtil.getEntityManager();
			// EntityTransaction transaction = manager.getTransaction();
			Object o = manager.merge(empresa);
			userTransaction.begin();
			manager.remove(o);
			userTransaction.commit();
			manager.close();
			empresa = new Empresa();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Empresa removida com sucesso!", null));
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

		return "empresa";
	}

	// MUDAR DE PAGINA

	public String cadastrar() {
		this.empresa = new Empresa();
		return "cadEmpresa";
	}

	public String editar() {
		return "editEmpresa";
	}

	public String view() {
		return "viewEmpresa";
	}

	public String cancela() {
		this.empresa = new Empresa();
		return "empresa";
	}

	public String limpar() {
		this.empresa = new Empresa();
		return "empresa";
	}

	// lista datatable
	public List<Empresa> getTodas() {
		EntityManager manager = null;

		try {
			manager = JpaUtil.getEntityManager();
			/*
			 * if (!this.empresa.getNome().equals("") &&
			 * !this.empresa.getNome().equals(null)) { TypedQuery<Empresa> query =
			 * manager.createQuery("from Empresa e where e.nome=:nome order by e.nome",
			 * Empresa.class); query.setParameter("nome", this.empresa.getNome());
			 * 
			 * return query.getResultList(); }
			 */// else {
				// TypedQuery<Empresa> query = manager.createQuery("select e from Empresa e
				// order by e.nome", Empresa.class);
				// tamanho = query.getResultList();
				// return query.getResultList();
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<Empresa> criteria = builder.createQuery(Empresa.class);
			Root<Empresa> root = criteria.from(Empresa.class);
			Path<Long> idEmpresa = root.get("idEmpresa");
			Path<Integer> siscad = root.get("siscad");
			Path<String> cnpj = root.get("cnpj");
			Path<String> nome = root.get("nome");
			Path<Integer> conta = root.get("conta");
			Path<String> nomeContato = root.get("nomeContato");
			Path<String> telefone = root.get("telefone");
			Path<String> email = root.get("email");
			Path<String> nomeAnalista = root.get("nomeAnalista");
			Path<String> contatoAnalista = root.get("contatoAnalista");
			Path<String> emailAnalista = root.get("emailAnalista");
			Path<String> nomeResponsavel = root.get("nomeResponsavel");
			Path<String> contatoResponsavel = root.get("contatoResponsavel");
			Path<String> emailResponsavel = root.get("emailResponsavel");
			Path<String> nomeRespAgencia = root.get("nomeRespAgencia");
			Path<String> telefoneRespAgencia = root.get("telefoneRespAgencia");
			Path<String> emailRespAgencia = root.get("emailRespAgencia");

			criteria.multiselect(idEmpresa, siscad, cnpj, nome, conta, nomeContato, telefone, email, nomeAnalista,
					contatoAnalista, emailAnalista, nomeResponsavel, contatoResponsavel, emailResponsavel,
					nomeRespAgencia, telefoneRespAgencia, emailRespAgencia);
			criteria.orderBy(builder.asc(root.get("nome")));
			TypedQuery<Empresa> query = manager.createQuery(criteria);
			// tamanho = query.getResultList();
			return query.getResultList();
			// }

		} finally {
			manager.close();
		}

	}

	public List<SelectItem> getAgencias() {
		List<SelectItem> items = new ArrayList<SelectItem>();

		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Agencia> query = manager.createQuery("from Agencia", Agencia.class);
			agencias = query.getResultList();
			for (Agencia a : this.agencias) {
				items.add(new SelectItem(a, a.getNome()));
			}
			return items;
		} finally {
			manager.close();
		}

	}

	// getters and setters

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public void setAgencias(List<Agencia> agencias) {
		this.agencias = agencias;
	}

	public List<Empresa> getEmpresasFiltradas() {
		return empresasFiltradas;
	}

	public void setEmpresasFiltradas(List<Empresa> empresasFiltradas) {
		this.empresasFiltradas = empresasFiltradas;
	}
	

}
