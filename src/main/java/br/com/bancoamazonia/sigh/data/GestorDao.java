package br.com.bancoamazonia.sigh.data;

import javax.persistence.EntityManager;

import br.com.bancoamazonia.sigh.model.Gestor;
import br.com.bancoamazonia.sigh.controller.JpaUtil;

public class GestorDao {
	private EntityManager manager = JpaUtil.getEntityManager();
	
	public Gestor getById(Long id) {
		try{
			return this.manager.find(Gestor.class, id);
		}finally{
			this.manager.close();
		}
		
	}
}
