package br.com.bancoamazonia.sigh.data;

import javax.persistence.EntityManager;

import br.com.bancoamazonia.sigh.model.Situacao_Homologacao_Sistema;
import br.com.bancoamazonia.sigh.controller.JpaUtil;

public class SituacaoHomologacaoSistemasDao {
private EntityManager manager = JpaUtil.getEntityManager();
	
	public Situacao_Homologacao_Sistema getById(Long id) {
		try{
			return this.manager.find(Situacao_Homologacao_Sistema.class, id);
		}finally{
			this.manager.close();
		}
		
	}
}
