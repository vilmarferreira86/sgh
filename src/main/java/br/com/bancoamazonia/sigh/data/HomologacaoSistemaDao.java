package br.com.bancoamazonia.sigh.data;

import javax.persistence.EntityManager;

import br.com.bancoamazonia.sigh.model.Homologacao_Sistemas;
import br.com.bancoamazonia.sigh.controller.JpaUtil;

public class HomologacaoSistemaDao {
private EntityManager manager = JpaUtil.getEntityManager();
	
	public Homologacao_Sistemas getById(Long id) {
		try{
			return this.manager.find(Homologacao_Sistemas.class, id);
		}finally{
			this.manager.close();
		}
		
	}
}
