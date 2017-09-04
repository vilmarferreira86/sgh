package br.com.bancoamazonia.sigh.data;

import javax.persistence.EntityManager;

import br.com.bancoamazonia.sigh.model.Analista;
import br.com.bancoamazonia.sigh.controller.JpaUtil;

public class AnalistaDao {
	private EntityManager manager = JpaUtil.getEntityManager();
	
	
	
	public Analista getById(Long id){
		try{
			return this.manager.find(Analista.class, id);
		}finally{
			this.manager.close();
		}
		
	}
}
