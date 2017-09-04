package br.com.bancoamazonia.sigh.data;

import javax.persistence.EntityManager;

import br.com.bancoamazonia.sigh.model.Empresa;
import br.com.bancoamazonia.sigh.controller.JpaUtil;

public class EmpresaDao {
	private EntityManager manager = JpaUtil.getEntityManager();

	public Empresa getById(Long id) {
		try{
			return this.manager.find(Empresa.class, id);
		}finally{
			this.manager.close();
		}
		
	}
}
