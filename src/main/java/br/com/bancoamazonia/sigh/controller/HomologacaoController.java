package br.com.bancoamazonia.sigh.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import javax.servlet.ServletContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

//import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
/*import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;*/

import br.com.bancoamazonia.sigh.controller.JpaUtil;
import br.com.bancoamazonia.sigh.model.Agencia;
import br.com.bancoamazonia.sigh.model.Analista;
import br.com.bancoamazonia.sigh.model.Empresa;
import br.com.bancoamazonia.sigh.model.Hml_Situacao;
import br.com.bancoamazonia.sigh.model.Homologacao;
import br.com.bancoamazonia.sigh.model.Situacao;
import br.com.bancoamazonia.sigh.model.Usuario;

@ManagedBean
@SessionScoped
public class HomologacaoController {
	private Homologacao homologacao = new Homologacao();
	private Hml_Situacao hml_situacao = new Hml_Situacao();
	private List<Analista> analistas = new ArrayList<Analista>();
	private List<Situacao> situacoes = new ArrayList<Situacao>();
	private Usuario usuario = new Usuario();

	// private List<Empresa> empresas = new ArrayList<Empresa>();
	// private List<Homologacao> filteredHomologacoes;
	private List<Hml_Situacao> homologacoesFiltradas;
	private List<Hml_Situacao> homologacoes;
	public List<Homologacao> hmls;
	private List<Homologacao> tamanho; // pega o tamanho da lista do data table
	private List<Homologacao> selectedHmls;
	private List<Homologacao> opcoes;
	// regra de botões
	private boolean value = false;
	private boolean valueBottom = true;
	private boolean valueNew = false;
	private boolean valueNN = false;
	private int faixaInicial;
	private int faixaFinal;
	private String opcao;
	@Inject
	UserTransaction userTransaction;
	// lista de agências
	private List<Agencia> agencias = new ArrayList<Agencia>();

	public HomologacaoController() {
		// TODO Auto-generated constructor stub
	}

	public HomologacaoController(Homologacao homologacao) {
		super();

		this.homologacao = homologacao;

	}

	/*
	 * public void handleFileUpload(FileUploadEvent event) throws IOException {
	 * try { FacesContext facesContext = FacesContext.getCurrentInstance();
	 * ServletContext scontext = (ServletContext) facesContext
	 * .getExternalContext().getContext(); String arquivo =
	 * scontext.getRealPath("/upload/" + event.getFile().getFileName());
	 * System.out.println("Arquivo--->" + arquivo); // FileOutputStream fos =
	 * new FileOutputStream(arquivo); byte[] bytes = IOUtils
	 * .toByteArray(event.getFile().getInputstream()); /* fos.write(bytes);
	 * fos.flush(); fos.close();
	 */
	/*
	 * EntityManager manager = JpaUtil.getEntityManager(); EntityTransaction
	 * transaction = manager.getTransaction(); transaction.begin();
	 * this.arquivo.setNome(event.getFile().getFileName());
	 * this.arquivo.setArquivo(bytes);
	 * this.hml_situacao.setArquivo(this.arquivo);
	 * manager.persist(this.arquivo); transaction.commit(); manager.close();
	 * FacesMessage message = new FacesMessage("Succesful", event
	 * .getFile().getFileName() + " is uploaded.");
	 * FacesContext.getCurrentInstance().addMessage(null, message); } catch
	 * (FileNotFoundException ex) {
	 * Logger.getLogger(HomologacaoController.class.getName()).log(
	 * Level.SEVERE, null, ex); } catch (IOException ex) {
	 * Logger.getLogger(HomologacaoController.class.getName()).log(
	 * Level.SEVERE, null, ex); }
	 * 
	 * }
	 */

	// persistindo na base de dadosa
	/*
	 * public String adicionar() { System.out.println("EMPRESA -> "
	 * +homologacao.getEmpresa ().getIdEmpresa()); try {
	 * 
	 * EntityManager manager = JpaUtil.getEntityManager(); EntityTransaction
	 * transaction = manager.getTransaction(); transaction.begin();
	 * 
	 * manager.persist(homologacao); transaction.commit(); manager.close();
	 * 
	 * // this.homologacao = new Homologacao();
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage(FacesMessage.SEVERITY_INFO,
	 * "Homologação cadastrada com sucesso!", null)); } catch
	 * (PersistenceException e) { FacesContext.getCurrentInstance().addMessage(
	 * null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e .getMessage(),
	 * null)); } return "cadHomologacao"; }
	 */

	public String adicionar() {
		try {
			System.out.println("Usuario atualizador->"+hml_situacao.getUsuario().getUsername());
			Context context = new InitialContext();
			userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
			EntityManager manager = JpaUtil.getEntityManager();
			//EntityTransaction transaction = manager.getTransaction();
			userTransaction.begin();
			// homologacao.setFaixaNossoNumero(faixaInicial + "-" + faixaFinal);
			homologacao.setSituacaoAtual(hml_situacao.getSituacao().getDescricao());
			homologacao.setDataAtualizacaoSituacao(new Date());
			homologacao.setSituacao("Em andamento");
			hml_situacao.setHomologacao(homologacao);
			// hml_situacao.setSituacao(hml_situacao.getSituacao());
			hml_situacao.setDataAtualizacao(new Date());
			manager.persist(hml_situacao);
			userTransaction.commit();
			manager.close();

			this.homologacao = new Homologacao();
			this.valueBottom = true;
			this.value = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Homologação cadastrada com sucesso!", null));
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

		return "homologacao";

	}

	public void novo() {
		
		this.hml_situacao.setObservacao(null);
		this.hml_situacao.setSituacao(new Situacao());


	}

	public void add() {
		System.out.println("Id da Homologação: "+this.hml_situacao.getHomologacao().getIdHomologacao());
		System.out.println("Id da Situação: "+this.hml_situacao.getSituacao().getIdSituacao());
		System.out.println("Usuario do Sistema: "+this.hml_situacao.getUsuario().getUsername());
		System.out.println("Observação: "+this.hml_situacao.getObservacao());
		try {
			
			
			this.hml_situacao.setDataAtualizacao(new Date());
			this.hml_situacao.getHomologacao().setSituacaoAtual(this.hml_situacao.getSituacao().getDescricao());
			this.hml_situacao.getHomologacao().setDataAtualizacaoSituacao(this.hml_situacao.getDataAtualizacao());

			// hml_situacao.setUsuario(usuario);
			Context context = new InitialContext();
			userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
			EntityManager manager = JpaUtil.getEntityManager();
			userTransaction.begin();

			manager.merge(this.hml_situacao);
			userTransaction.commit();
			manager.close();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Homologação cadastrada com sucesso!", null));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), null));
		}
	}

	// METODO UPDATE
	public String update() {
		try {
			Date data = new Date();
			Context context = new InitialContext();
			userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
			EntityManager manager = JpaUtil.getEntityManager();
			//EntityTransaction transaction = manager.getTransaction();
			userTransaction.begin();
			System.out.println("RECEBENDO DATA INICIAL ->" + homologacao.getDataInicio());
			manager.merge(homologacao);
			userTransaction.commit();
			manager.close();
			this.homologacao = new Homologacao();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Homologação atualizada com sucesso!", null));
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

		return "homologacao";
	}

	// método para mudar o status da homologação de 'Em Andamento' para
	// 'Concluída'
	public void fechar() {
		if (this.selectedHmls != null) {
			for (int i = 0; i < this.selectedHmls.size(); i++) {

				this.homologacao.setIdHomologacao(this.selectedHmls.get(i).getIdHomologacao());
				try {
					
					Context context = new InitialContext();
					userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
					EntityManager manager = JpaUtil.getEntityManager();
					userTransaction.begin();
					// this.homologacao.setSituacao("Concluído");
					// this.homologacao.setSituacaoAtual("Concluído");
					this.homologacao.setDataAtualizacaoSituacao(new Date());

					Query q = manager.createQuery(
							"update Homologacao set situacao='Concluido', situacaoAtual='Em Produção' Where idHomologacao = :idHomologacao");
					q.setParameter("idHomologacao", homologacao.getIdHomologacao());
					// manager.remove(o);
					q.executeUpdate();
					userTransaction.commit();
					manager.close();
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									"Homologação Concluída! Informe a faixa de NN de produção das cobranças pré-impressas.",
									null));
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

		}
		this.homologacao = new Homologacao();
		this.selectedHmls = new ArrayList<Homologacao>();
	}

	// método para mudar o status da homologação de 'Em Andamento' para
	// 'Cancelada'
	public void cancelar() {
		if (this.selectedHmls != null) {
			for (int i = 0; i < this.selectedHmls.size(); i++) {

				this.homologacao.setIdHomologacao(this.selectedHmls.get(i).getIdHomologacao());
				try {
					Context context = new InitialContext();
					userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
					EntityManager manager = JpaUtil.getEntityManager();
					//EntityTransaction transaction = manager.getTransaction();
					userTransaction.begin();
					// this.homologacao.setConcluida(true);
					// this.homologacao.setSituacaoAtual("Concluído");
					this.homologacao.setDataAtualizacaoSituacao(new Date());

					Query q = manager.createQuery(
							"update Homologacao set situacao='Cancelada', situacaoAtual='Cancelada' Where idHomologacao = :idHomologacao");
					q.setParameter("idHomologacao", homologacao.getIdHomologacao());
					// manager.remove(o);
					q.executeUpdate();
					userTransaction.commit();
					manager.close();
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Homologação Concluída!", null));
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

		}
		this.homologacao = new Homologacao();
		this.selectedHmls = new ArrayList<Homologacao>();
	}

	// atualiza tabela hml_sit da base de dados
	public String atualizar() {
		try {
			Context context = new InitialContext();
			userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
			EntityManager manager = JpaUtil.getEntityManager();
			//EntityTransaction transaction = manager.getTransaction();
			userTransaction.begin();
			hml_situacao.setDataAtualizacao(new Date());
			manager.merge(hml_situacao);
			userTransaction.commit();
			manager.close();
			// this.hml_situacao = new Homologacao();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Histórico atualizado com sucesso!", null));
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

		return "configure";

	}

	// METODO REMOVER
	public void remove() {
		try {
			Context context = new InitialContext();
			userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
			EntityManager manager = JpaUtil.getEntityManager();
			//EntityTransaction transaction = manager.getTransaction();
			System.out.println("idHomologação-->" + homologacao.getIdHomologacao());
			// hml_situacao.setHomologacao(homologacao);

			userTransaction.begin();
			Object o = manager.merge(homologacao);

			manager.remove(o);

			userTransaction.commit();
			manager.close();
			// homologacao = new Homologacao();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Homologação removida com sucesso!", null));
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

		// return "homologacao";
	}

	public void displayNN() {
		System.out.println("Valor da opção ->" + homologacao.getOpcao());
		if (this.homologacao.getOpcao().equals("") || this.homologacao.getOpcao().equals(null)) {
			System.out.println("ESTOU DENTRO DA OPÇÃO IGUAL A NULL");
			this.valueNN = false;
		} else if (homologacao.getOpcao().equals("Pre-Impressa")) {

			valueNN = true;
			System.out.println(
					"ESTOU DENTRO DA OPÇÃO IGUAL A Cliente Imprime os Boletos com Ranger de Nosso Numero e o valueNN é igual a"
							+ valueNN);
		} else {
			valueNN = false;
		}

	}

	// deleta na hml_situacao
	public void delete() {
		try {
			Context context = new InitialContext();
			userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
			EntityManager manager = JpaUtil.getEntityManager();
			//EntityTransaction transaction = manager.getTransaction();

			// Object o = manager.merge(hml_situacao);

			userTransaction.begin();
			Query q = manager.createQuery("delete Hml_Situacao Where idHmlSit = :idHmlSit");
			q.setParameter("idHmlSit", hml_situacao.getIdHmlSit());
			// manager.remove(o);
			q.executeUpdate();
			userTransaction.commit();
			manager.close();
			hml_situacao.setIdHmlSit(null);
			// hml_situacao = new Hml_Situacao();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Situação removida com sucesso!", null));
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

	// botao para limpar formulario
	public void clear() {
		if (this.faixaInicial != 0) {
			this.faixaInicial = 0;
		}
		if (this.faixaFinal != 0) {
			this.faixaFinal = 0;
		}

		this.opcao = null;
		this.homologacao = new Homologacao();
		getTodas();
	}

	// lista datatable
	public List<Homologacao> getTodas() {
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			
			if ((this.homologacao.getFaixaIniProd() != 0 || this.homologacao.getFaixaFimProd() != 0)
					&& (this.homologacao.getFaixaIniProd() != this.homologacao.getFaixaFimProd())) {
				Query query = manager.createQuery(
						"from Homologacao h where h.faixaIniProd>=:inicial and h.faixaFimProd<=:final order by h.dataInicio desc, h.empresa.nome");
				query.setParameter("inicial", this.homologacao.getFaixaIniProd());
				query.setParameter("final", this.homologacao.getFaixaFimProd());
				return query.getResultList();

			} else if ((this.homologacao.getFaixaIniProd() == this.homologacao.getFaixaFimProd())
					&& (this.homologacao.getFaixaIniProd() != 0 && this.homologacao.getFaixaFimProd() != 0)) {
				Query query = manager.createQuery(
						"from Homologacao h where h.faixaIniProd between 1000000 and :inicial and h.faixaFimProd>=:final order by h.dataInicio desc, h.empresa.nome");
				query.setParameter("inicial", this.homologacao.getFaixaIniProd());
				query.setParameter("final", this.homologacao.getFaixaFimProd());
				return query.getResultList();

			} else {
				TypedQuery<Homologacao> query = manager.createQuery(
						"from Homologacao h order by h.dataInicio desc, h.empresa.nome", Homologacao.class);

				return query.getResultList();
			}


		} finally {
			manager.close();
		}

	}

	// metodo do auto complete
	public List<Empresa> sugerirEmpresas(String query) {
		System.out.println("query ->" + query);
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Empresa> q = manager.createQuery("from Empresa e where e.nome like upper(:nome)", Empresa.class);
			q.setParameter("nome", query + "%");
			return q.getResultList();
		} finally {
			manager.close();
		}

	}

	public List<SelectItem> getAnalistas() {
		List<SelectItem> items = new ArrayList<SelectItem>();

		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Analista> query = manager.createQuery("from Analista", Analista.class);
			analistas = query.getResultList();
			for (Analista a : this.analistas) {
				items.add(new SelectItem(a, a.getNome()));
			}
			return items;
		} finally {
			manager.close();
		}

	}

	public List<SelectItem> getSituacoes() {
		List<SelectItem> items = new ArrayList<SelectItem>();

		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Situacao> query = manager.createQuery("from Situacao", Situacao.class);
			situacoes = query.getResultList();
			for (Situacao s : this.situacoes) {
				items.add(new SelectItem(s, s.getDescricao()));
			}
			return items;
		} finally {
			manager.close();
		}

	}

	public boolean valores() {
		this.homologacao = new Homologacao();
		this.hml_situacao = new Hml_Situacao();
		if (this.value == false) {
			this.value = true;
		}
		if (this.valueBottom == true) {
			this.valueBottom = false;
		}

		if (this.valueNew == false) {
			this.valueNew = true;
		}
		return value;
	}

	public String editarconfiguracoes() {
		return "editConfigure";
	}

	public List<SelectItem> getAgencias() {
		List<SelectItem> items = new ArrayList<SelectItem>();

		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Agencia> query = manager.createQuery("from Agencia a ORDER BY a.numero", Agencia.class);
			agencias = query.getResultList();
			for (Agencia a : this.agencias) {
				items.add(new SelectItem(a, a.getNumero() + "-" + a.getNome()));
			}
			return items;
		} finally {
			manager.close();
		}

	}

	public List<SelectItem> getSuperintendencias() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Agencia> q = manager.createQuery("FROM Agencia a WHERE a.numero > 7000 order by a.nome",
					Agencia.class);
			agencias = q.getResultList();
			for (Agencia a : this.agencias) {
				items.add(new SelectItem(a, a.getNumero() + "-" + a.getNome()));
			}
			return items;
		} finally {
			manager.close();
		}
	}

	// MUDAR DE PAGINA
	public String editar() {
		System.out.println("estou aqui no metodo editar para mudar de pagina");
		return "editHomologacao";
	}

	// mudar pagina para cadHomologacao
	public String cad() {
		this.homologacao = new Homologacao();
		this.hml_situacao = new Hml_Situacao();
		return "cadHomologacao";

	}

	public String limpar() {
		this.homologacao = new Homologacao();
		return "homologacao";
	}

	public String cancela() {
		this.homologacao = new Homologacao();
		this.value = false;
		this.valueBottom = true;
		this.valueNN = false;
		return "homologacao";
	}

	public String cancelarconf() {
		return "configure";
	}

	public String configure() {
		this.hml_situacao.setHomologacao(homologacao);
		return "configure";
	}

	public List<Hml_Situacao> getHomologacoesFiltradas() {
		return homologacoesFiltradas;
	}

	public void setHomologacoesFiltradas(List<Hml_Situacao> homologacoesFiltradas) {
		this.homologacoesFiltradas = homologacoesFiltradas;
	}

	public Homologacao getHomologacao() {
		return homologacao;
	}

	public void setHomologacao(Homologacao homologacao) {
		this.homologacao = homologacao;
	}

	public List<Hml_Situacao> getHomologacoes() {

		this.hml_situacao.setHomologacao(this.homologacao);
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Hml_Situacao> query = manager.createQuery(
					"from Hml_Situacao h WHERE h.homologacao.idHomologacao = :idHomologacao ORDER BY h.dataAtualizacao desc",
					Hml_Situacao.class);
			query.setParameter("idHomologacao", hml_situacao.getHomologacao().getIdHomologacao());
			homologacoes = query.getResultList();
			return homologacoes;
		} finally {
			manager.close();
		}

	}

	public void setHomologacoes(List<Hml_Situacao> homologacoes) {
		this.homologacoes = homologacoes;
	}
	
	

	public Hml_Situacao getHml_situacao() {
		return hml_situacao;
	}

	public void setHml_situacao(Hml_Situacao hml_situacao) {
		this.hml_situacao = hml_situacao;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public boolean isValueBottom() {
		return valueBottom;
	}

	public void setValueBottom(boolean valueBottom) {
		this.valueBottom = valueBottom;
	}

	public void setAgencias(List<Agencia> agencias) {
		this.agencias = agencias;
	}

	public boolean isValueNew() {
		return valueNew;
	}

	public void setValueNew(boolean valueNew) {
		this.valueNew = valueNew;
	}

	public List<Homologacao> getHmls() {
		return hmls;
	}

	public void setHmls(List<Homologacao> hmls) {
		this.hmls = hmls;
	}

	/*public int getTamanho() {
		return this.tamanho.size();
	}*/

	public void setTamanho(List<Homologacao> tamanho) {
		this.tamanho = tamanho;
	}

	public List<Homologacao> getSelectedHmls() {
		return selectedHmls;
	}

	public void setSelectedHmls(List<Homologacao> selectedHmls) {
		this.selectedHmls = selectedHmls;
	}

	public List<Homologacao> getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(List<Homologacao> opcoes) {
		this.opcoes = opcoes;
	}

	public void setAnalistas(List<Analista> analistas) {
		this.analistas = analistas;
	}

	public void setSituacoes(List<Situacao> situacoes) {
		this.situacoes = situacoes;
	}

	public boolean isValueNN() {
		return valueNN;
	}

	public void setValueNN(boolean valueNN) {
		this.valueNN = valueNN;
	}

	public int getFaixaInicial() {
		return faixaInicial;
	}

	public void setFaixaInicial(int faixaInicial) {
		this.faixaInicial = faixaInicial;
	}

	public int getFaixaFinal() {
		return faixaFinal;
	}

	public void setFaixaFinal(int faixaFinal) {
		this.faixaFinal = faixaFinal;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getOpcao() {
		return opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}

}
