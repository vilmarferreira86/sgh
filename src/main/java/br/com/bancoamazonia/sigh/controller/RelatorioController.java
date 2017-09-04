package br.com.bancoamazonia.sigh.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;

import br.com.bancoamazonia.sigh.controller.JpaUtil;
import br.com.bancoamazonia.sigh.model.Homologacao;
import br.com.bancoamazonia.sigh.model.Homologacao_Sistemas;
import br.com.bancoamazonia.sigh.model.Sdm;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean
@SessionScoped
public class RelatorioController {

	private String tipo = null;
	private Homologacao homologacao = new Homologacao();
	private Sdm sdm = new Sdm();
	private Date dataInicio;
	private Date dataFinal;

	public RelatorioController() {
	}

	public RelatorioController(Homologacao homologacao) {
		super();
		this.homologacao = homologacao;

	}
	
	public void clear(){
		if(this.dataInicio != null && this.dataFinal != null){
			this.dataInicio = null;
			this.dataFinal = null;
		}
	}

	public void pdf() throws IOException {
		try {

			List<Homologacao> listHml = null;
			JRBeanCollectionDataSource beanCollectionDataSource;

			FacesContext context = FacesContext.getCurrentInstance();
			EntityManager em = JpaUtil.getEntityManager();
			if (this.homologacao.getSituacao().equals("Todos")
					&& this.homologacao.getPadrao().equals("Todos")) {
				Query q = em
						.createQuery("select h from Homologacao h order by h.agencia.nome,h.empresa.nome)");

				listHml = (List<Homologacao>) q.getResultList();
				beanCollectionDataSource = new JRBeanCollectionDataSource(
						listHml);
			} else if (!this.homologacao.getSituacao().equals("Todos")
					&& this.homologacao.getPadrao().equals("Todos")) {
				Query q = em
						.createQuery("select h from Homologacao h WHERE h.situacao=:status order by h.agencia.nome,h.empresa.nome)");
				q.setParameter("status", this.homologacao.getSituacao());
				listHml = (List<Homologacao>) q.getResultList();
				beanCollectionDataSource = new JRBeanCollectionDataSource(
						listHml);
			} else if (this.homologacao.getSituacao().equals("Todos")
					&& !this.homologacao.getPadrao().equals("Todos")) {
				Query q = em
						.createQuery("select h from Homologacao h WHERE h.padrao=:padrao order by h.agencia.nome,h.empresa.nome)");
				q.setParameter("padrao", this.homologacao.getPadrao());
				listHml = (List<Homologacao>) q.getResultList();
				beanCollectionDataSource = new JRBeanCollectionDataSource(
						listHml);
			} else {
				Query q = em
						.createQuery("select h from Homologacao h WHERE h.situacao=:status AND h.padrao=:padrao order by h.agencia.nome,h.empresa.nome)");
				q.setParameter("status", this.homologacao.getSituacao());
				q.setParameter("padrao", this.homologacao.getPadrao());
				listHml = (List<Homologacao>) q.getResultList();
				beanCollectionDataSource = new JRBeanCollectionDataSource(
						listHml);
			}

			// q.executeUpdate();

			// compila jrxml em memoria
			// JasperReport jasper = JasperCompileManager.compileReport(jrxml);

			// caminho do relatorio ja compilado
			String reportPath = context.getExternalContext().getRealPath(
					"/relatorios/cobranca2.jasper");

			// preenche o relatorio
			JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,
					new HashMap(), beanCollectionDataSource);
			// JasperExportManager.exportReportToPdfFile(jasperPrint,"C:/relatorios/relatorio.pdf");/
			// exportar relatorio to pdf

			HttpServletResponse response = (HttpServletResponse) context
					.getExternalContext().getResponse();

			// response.setHeader("Content-Disposition",
			// "attachment; filename=\"relatorio.pdf\"");
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition",
					"inline; filename=\"relatorio.pdf");

			JasperExportManager.exportReportToPdfStream(jasperPrint,
					response.getOutputStream());
			context.getApplication().getStateManager().saveView(context);
			context.renderResponse();
			context.responseComplete();
			em.close();

			// JasperViewer.viewReport(jasperPrint,false);

			/*
			 * byte[] bytes = null; bytes =
			 * JasperRunManager.runReportToPdf(reportPath, new HashMap(),
			 * beanCollectionDataSource); response.setHeader("Cache-Control",
			 * "no-store"); response.setHeader("Pragma", "no-cache");
			 * response.setDateHeader("Expires", 0);
			 * response.setContentType("application/pdf");
			 * response.setContentLength(bytes.length);
			 * 
			 * // ServletOutputStream servletOutputStream = //
			 * response.getOutputStream(); // servletOutputStream.write(bytes,
			 * 0, bytes.length); // servletOutputStream.flush();
			 * 
			 * // JasperExportManager.exportReportToPdfStream(jasperPrint, //
			 * servletOutputStream); // servletOutputStream.close(); //
			 * FacesContext.getCurrentInstance().responseComplete();
			 */

		} catch (JRException e) {
			System.out.println("Erro ao gerar relatorio->" + e.getMessage());
			e.printStackTrace();
		} // return "relatorio";
	}

	// gera relatorio da sdm
	public void sdm() throws IOException {
		try {

			List<Homologacao_Sistemas> listHml = new ArrayList<Homologacao_Sistemas>();
			JRBeanCollectionDataSource beanCollectionDataSource;

			FacesContext context = FacesContext.getCurrentInstance();
			EntityManager em = JpaUtil.getEntityManager();
			Query query = em
					.createQuery("from Homologacao_Sistemas h WHERE h.sdm.dataExc BETWEEN :inicio and :fim  order by h.sdm.numero)");
			query.setParameter("inicio", dataInicio);
			query.setParameter("fim", dataFinal);
			// Query q =
			// em.createQuery("SELECT s.numero from Sdm s JOIN s.homologacoes as homologacao order by s.numero)");
			// Query q =
			// em.createQuery("from Sdm s, Homologacao_Sistemas h WHERE s.id_sdm=h.sdm.id_sdm order by s.numero)");
			listHml = query.getResultList();

			/*
			 * for(Object[] elements : listHml2){ Object[] aux = elements; //
			 * Sdm s = new Sdm(); String numero = String.valueOf(aux[0]);
			 * //s.setNumero((int) aux[0]);
			 * System.out.println("Numero da SDM ------>"+numero);
			 * //s.setId_sdm(id_sdm); //listHml.add(s);
			 * 
			 * }
			 */

			// for(int i = 0; i < listHml2.size(); i++){
			// System.out.println("Numero da SDM ------>"+listHml2.get(i).toString());
			/*
			 * Sdm s = new Sdm(); s.setId_sdm(listHml2.get(i).getId_sdm());
			 * s.setDataInicio(listHml2.get(i).getDataInicio());
			 * s.setDataExc(listHml2.get(i).getDataExc());
			 * s.setDescricao(listHml2.get(i).getDescricao());
			 * s.setNumero(listHml2.get(i).getNumero());
			 * s.setSituacao(listHml2.get(i).getSituacao());
			 * s.setTipo(listHml2.get(i).getTipo());
			 * s.setTitulo(listHml2.get(i).getTitulo());
			 * s.setHomologacoes(listHml2.get(i).getHomologacoes());
			 * //s.getHomologacoes
			 * ().get(i).setChamado(listHml2.get(i).getHomologacoes
			 * ().get(i).getChamado());
			 */

			// listHml.add(s);
			// }
			// System.out.println(listHml);
			// System.out.println("NUMERO DA SDM------------>"+listHml.get(0).getNumero());

			beanCollectionDataSource = new JRBeanCollectionDataSource(listHml);

			// q.executeUpdate();

			// compila jrxml em memoria
			// JasperReport jasper = JasperCompileManager.compileReport(jrxml);

			// caminho do relatorio ja compilado
			String reportPath = context.getExternalContext().getRealPath(
					"/relatorios/relSdm.jasper");

			// preenche o relatorio
			JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,
					new HashMap(), beanCollectionDataSource);
			// JasperExportManager.exportReportToPdfFile(jasperPrint,"C:/relatorios/relatorio.pdf");/
			// exportar relatorio to pdf

			HttpServletResponse response = (HttpServletResponse) context
					.getExternalContext().getResponse();

			// response.setHeader("Content-Disposition",
			// "attachment; filename=\"relatorio.pdf\"");
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition",
					"inline; filename=\"relatorioSdm.pdf");

			JasperExportManager.exportReportToPdfStream(jasperPrint,
					response.getOutputStream());
			context.getApplication().getStateManager().saveView(context);
			context.renderResponse();
			context.responseComplete();
			em.close();

			// JasperViewer.viewReport(jasperPrint,false);

			/*
			 * byte[] bytes = null; bytes =
			 * JasperRunManager.runReportToPdf(reportPath, new HashMap(),
			 * beanCollectionDataSource); response.setHeader("Cache-Control",
			 * "no-store"); response.setHeader("Pragma", "no-cache");
			 * response.setDateHeader("Expires", 0);
			 * response.setContentType("application/pdf");
			 * response.setContentLength(bytes.length);
			 * 
			 * // ServletOutputStream servletOutputStream = //
			 * response.getOutputStream(); // servletOutputStream.write(bytes,
			 * 0, bytes.length); // servletOutputStream.flush();
			 * 
			 * // JasperExportManager.exportReportToPdfStream(jasperPrint, //
			 * servletOutputStream); // servletOutputStream.close(); //
			 * FacesContext.getCurrentInstance().responseComplete();
			 */

		} catch (JRException e) {
			System.out.println("Erro ao gerar relatorio->" + e.getMessage());
			e.printStackTrace();
		} // return "relatorio";
	}

	public Homologacao getHomologacao() {
		return homologacao;
	}

	public void setHomologacao(Homologacao homologacao) {
		this.homologacao = homologacao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Sdm getSdm() {
		return sdm;
	}

	public void setSdm(Sdm sdm) {
		this.sdm = sdm;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	

}
