package br.com.myproject.minipar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.myproject.minipar.models.TipoRecebivel;

@Repository @Transactional
public class TipoRecebivelDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public List<TipoRecebivel> listaTipoRecebivel(){
		return manager.createQuery("select t from TipoRecebivel t", TipoRecebivel.class).getResultList();
	}	
	
	public TipoRecebivel find(Integer tipo) {
		return  manager.find(TipoRecebivel.class, tipo);
	}
	
	
	
}
