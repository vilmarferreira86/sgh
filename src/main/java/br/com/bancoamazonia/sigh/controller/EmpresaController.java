package br.com.bancoamazonia.sigh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.bancoamazonia.sigh.controller.JpaUtil;
import br.com.bancoamazonia.sigh.model.Agencia;
import br.com.bancoamazonia.sigh.model.Empresa;
import br.com.bancoamazonia.sigh.model.Homologacao;

@ManagedBean
@SessionScoped
public class EmpresaController {
	private Empresa empresa = new Empresa();
	private List<Agencia> agencias = new ArrayList<Agencia>();
	private List<Empresa> tamanho; // pega o tamanho da lista do data table

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
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Nome da empresa é obrigatório!", null));
			} else if (empresa.getSiscad() == 0) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Siscad da empresa é obrigatório!", null));
			}

			else if (empresa.getConta() == 0) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Conta da empresa é obrigatória!", null));
			} else {
				EntityManager manager = JpaUtil.getEntityManager();
				EntityTransaction transaction = manager.getTransaction();
				transaction.begin();
				empresa.setNome(empresa.getNome().toUpperCase());

				manager.persist(empresa);
				transaction.commit();
				manager.close();

				this.empresa = new Empresa();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Empresa cadastrada com sucesso!", null));
			}

		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
		}
		return "empresa";
	}

	// botao para limpar formulario
	public void clear() {
		this.empresa = new Empresa();
	}
	
	// navegar de empresa para viewEmmpresa
	public String goViewEmpresa(){
		return "viewEmpresa";
	}

	public void add() {
		System.out.println("empresa nome->" + empresa.getNome());
		/*
		 * try { if (empresa.getNome().equals("")) {
		 * FacesContext.getCurrentInstance().addMessage( null, new
		 * FacesMessage(FacesMessage.SEVERITY_ERROR,
		 * "Nome da empresa é obrigatório!", null)); } else if
		 * (empresa.getSiscad() == 0){
		 * FacesContext.getCurrentInstance().addMessage( null, new
		 * FacesMessage(FacesMessage.SEVERITY_ERROR,
		 * "Siscad da empresa é obrigatório!", null)); }
		 * 
		 * else if(empresa.getConta() == 0){
		 * FacesContext.getCurrentInstance().addMessage( null, new
		 * FacesMessage(FacesMessage.SEVERITY_ERROR,
		 * "Conta da empresa é obrigatória!", null)); } else { EntityManager
		 * manager = JpaUtil.getEntityManager(); EntityTransaction transaction =
		 * manager.getTransaction(); transaction.begin();
		 * empresa.setNome(empresa.getNome().toUpperCase());
		 * 
		 * manager.persist(empresa); transaction.commit(); manager.close();
		 * 
		 * this.empresa = new Empresa();
		 * FacesContext.getCurrentInstance().addMessage( null, new
		 * FacesMessage(FacesMessage.SEVERITY_INFO,
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
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Nome da empresa é obrigatório!", null));
			} else if (empresa.getSiscad() == 0) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Siscad da empresa é obrigatório!", null));
			}

			else if (empresa.getConta() == 0) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Conta da empresa é obrigatória!", null));
			} else {
				empresa.setNome(empresa.getNome().toUpperCase());
				EntityManager manager = JpaUtil.getEntityManager();
				EntityTransaction transaction = manager.getTransaction();
				transaction.begin();

				manager.merge(empresa);
				transaction.commit();
				manager.close();
				this.empresa = new Empresa();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Empresa atualizada com sucesso!", null));
			}

		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
		}

		return "empresa";
	}

	// METODO REMOVER
	public String remove() {
		try {

			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			Object o = manager.merge(empresa);
			transaction.begin();
			manager.remove(o);
			transaction.commit();
			manager.close();
			empresa = new Empresa();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Empresa removida com sucesso!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
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
	
	public String view(){
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
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			System.out.println("empresa--------->"+empresa.getNome());
			if(this.empresa.getNome()== null){
				this.empresa.setNome("");
			}
			if(!this.empresa.getNome().equals("")){
				Query query = manager.createQuery("from Empresa e where e.nome=:nome order by e.nome");
				query.setParameter("nome", this.empresa.getNome());
				
				tamanho = query.getResultList();
			}else{
				TypedQuery<Empresa> query = manager.createQuery(
						"from Empresa e order by e.nome",
						Empresa.class);
				tamanho = query.getResultList();
				return query.getResultList();
			}
			
			return tamanho;
			
		} finally {
			manager.close();
		}

	}

	public List<SelectItem> getAgencias() {
		List<SelectItem> items = new ArrayList<SelectItem>();

		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Agencia> query = manager.createQuery("from Agencia",
					Agencia.class);
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

	public int getTamanho() {
		return tamanho.size();
	}

	public void setTamanho(List<Empresa> tamanho) {
		this.tamanho = tamanho;
	}

}
