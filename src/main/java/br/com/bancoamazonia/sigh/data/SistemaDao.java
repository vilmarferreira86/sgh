package br.com.bancoamazonia.sigh.data;

import javax.persistence.EntityManager;

import br.com.bancoamazonia.sigh.model.Sistema;
import br.com.bancoamazonia.sigh.controller.JpaUtil;

public class SistemaDao {
	private EntityManager manager = JpaUtil.getEntityManager();
	
	public Sistema getById(Long id) {
		try{
			return this.manager.find(Sistema.class, id);

		}finally{
			this.manager.close();
		}
	}
}
