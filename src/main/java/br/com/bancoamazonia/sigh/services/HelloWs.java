package br.com.bancoamazonia.sigh.services;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class HelloWs {
	private String message = new String("Hello, ");
	
	public HelloWs(){}
	
	@WebMethod
	public String sayHello(String user, String password){
		return message + user + ", sua senha eh: " + password +".";
	}

}
