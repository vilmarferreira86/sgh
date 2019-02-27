package br.com.bancoamazonia.sigh.controller;

import java.rmi.RemoteException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;


import br.com.bancoamazonia.sigh.model.Usuario;
import br.com.bancoamazonia.sigh.util.SessionContext;
import br.com.pdcase.pdsegu.ws.AutenticaServiceWrapper;
import br.com.pdcase.pdsegu.ws.SeguServiceProxy;

@ManagedBean
@SessionScoped
public class UsuarioController {

	private Usuario usuario = new Usuario();

	public UsuarioController() {
	}

	public String login() {
		SeguServiceProxy wsdl = new SeguServiceProxy();
		System.out.println("ESTOU NO MÉTODO LOGIN()");
		try {
			String usuario = this.usuario.getUsername();
			String senha = this.usuario.getPassword();
			AutenticaServiceWrapper validarUsuario = wsdl.validarUsuario(usuario, senha);
			String resultado = validarUsuario.getResultado();
			System.out.print("Resultado -> " + validarUsuario.getResultado());
			if ("0".equals(resultado)) {
				this.usuario.setUsername(usuario);
				this.usuario.setPassword(senha);
				this.usuario.setNome(validarUsuario.getUsuario().getNome());
				SessionContext.getInstance().setAttribute("usuarioLogado", usuario);
				return "/usr/index";
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário ou senha inválidos!", null));
				return null;
			}
		} catch (RemoteException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			e.printStackTrace();
			return null;
		}

	}

	public String doLogout() {
		SessionContext.getInstance().encerrarSessao();
		return "/login";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
