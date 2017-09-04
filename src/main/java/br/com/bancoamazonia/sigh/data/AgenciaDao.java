package br.com.bancoamazonia.sigh.data;

import javax.persistence.EntityManager;

import br.com.bancoamazonia.sigh.model.Agencia;
import br.com.bancoamazonia.sigh.controller.JpaUtil;

public class AgenciaDao {
	private EntityManager manager = JpaUtil.getEntityManager();
	
	
	
	public Agencia getById(Long id){
		try{
			return this.manager.find(Agencia.class, id);
		}finally{
			this.manager.close();
		}
		
	}
}
