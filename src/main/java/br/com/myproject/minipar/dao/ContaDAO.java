package br.com.myproject.minipar.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.myproject.minipar.models.Conta;

@Repository @Transactional
public class ContaDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Conta conta) {
		
		if(conta.getId()==null) {
			manager.persist(conta);
		}else {
			manager.merge(conta);
		}
		
	}
	
	public Long findNumero(){	
		Long quantidade = manager.createQuery("select count(c) from Conta c", Long.class).getSingleResult();		
		return quantidade;
	}
	
	public Conta find(Integer id) {
		return manager.find(Conta.class, id);
	}
		
}
