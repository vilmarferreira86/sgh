package br.com.bancoamazonia.sigh.data;

import javax.persistence.EntityManager;

import br.com.bancoamazonia.sigh.model.Homologacao;
import br.com.bancoamazonia.sigh.controller.JpaUtil;

public class HomologacaoDao {
	private EntityManager manager = JpaUtil.getEntityManager();

	public Homologacao getById(Long id) {
		try{
			return this.manager.find(Homologacao.class, id);
		}finally{
			this.manager.close();
		}
		
	}
}
