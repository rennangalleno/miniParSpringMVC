package br.com.myproject.minipar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.myproject.minipar.models.SituacaoRecebivel;

@Repository @Transactional
public class SituacaoRecebivelDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public List<SituacaoRecebivel> listaSituacaoRecebivel(){
		return manager.createQuery("select s from SituacaoRecebivel s", SituacaoRecebivel.class).getResultList();
	}	
	
	public SituacaoRecebivel find(Integer situacao) {
		return  manager.find(SituacaoRecebivel.class, situacao);
	}
}
