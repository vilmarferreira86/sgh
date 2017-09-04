package br.com.bancoamazonia.sigh.data;

import javax.persistence.EntityManager;

import br.com.bancoamazonia.sigh.model.Situacao;
import br.com.bancoamazonia.sigh.controller.JpaUtil;

public class SituacaoDao {
	private EntityManager manager = JpaUtil.getEntityManager();

	public Situacao getById(Long id) {
		try{
			return this.manager.find(Situacao.class, id);

		}finally{
			this.manager.close();
		}
	}
}
