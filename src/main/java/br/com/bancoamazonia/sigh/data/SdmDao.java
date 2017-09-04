package br.com.bancoamazonia.sigh.data;

import javax.persistence.EntityManager;

import br.com.bancoamazonia.sigh.model.Sdm;
import br.com.bancoamazonia.sigh.controller.JpaUtil;

public class SdmDao {
private EntityManager manager = JpaUtil.getEntityManager();
	
	public Sdm getById(Long id) {
		try{
			return this.manager.find(Sdm.class, id);
		}finally{
			this.manager.close();
		}
		
	}

}
