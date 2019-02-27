package br.com.bancoamazonia.sigh.util;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.bancoamazonia.sigh.controller.UsuarioController;
import br.com.bancoamazonia.sigh.model.Usuario;

public class PageFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpSession session = ((HttpServletRequest) request).getSession();
		
		System.out.println("Sessao----> "+session.getAttribute("usuarioLogado"));
		String usuario = null;
		
		usuario = (String) session.getAttribute("usuarioLogado");

		if (usuario == null) {

			session.setAttribute("msg", "Você não está logado no sistema!");

			((HttpServletResponse) response).sendRedirect("/sigh/login.jsf");

		} else {

			chain.doFilter(request, response);

		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
