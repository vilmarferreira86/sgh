package br.com.pdcase.pdsegu.ws;

import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import br.com.pdcase.pdsegu.ws.AutenticaServiceWrapper;
import br.com.pdcase.pdsegu.ws.SeguServiceProxy;

public class Teste {

	public static void main(String[] args) {
		SeguServiceProxy wsdl = new SeguServiceProxy();
		try {
			AutenticaServiceWrapper validarUsuario = wsdl.validarUsuario("7639", "Vfmj1234");
			String resultado = validarUsuario.getResultado();
			if ("0".equals(resultado)) {
				JOptionPane.showMessageDialog(null, "Usuario: " + validarUsuario.getUsuario().getNome());
			} else {
				JOptionPane.showMessageDialog(null, validarUsuario.getMensagem());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
