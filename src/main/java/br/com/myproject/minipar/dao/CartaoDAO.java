package br.com.myproject.minipar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.myproject.minipar.models.Bandeira;
import br.com.myproject.minipar.models.Cartao;

@Repository @Transactional
public class CartaoDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Cartao cartao) {
		if(cartao.getId() == null) {
			manager.persist(cartao);
		} else {
			manager.merge(cartao);
		}
	}
	
	public List<Cartao> listar(){
		return manager.createQuery("select c from Cartao c "
				+ "join fetch c.bandeira "
				+ "join fetch c.cliente "
				+ "join fetch c.tipoRecebivel "
				+ "join fetch c.situacaoRecebivel "
				+ "where c.situacaoRecebivel = 1", Cartao.class).getResultList();	
	}
	
	public List<Bandeira> listaBandeira(){
		return manager.createQuery("select b from Bandeira b", Bandeira.class).getResultList();
	}
	
	public Cartao find(Integer id) {
		return manager.find(Cartao.class, id);
	}

	public List<Cartao> consultar(Integer clienteId, Integer bandeiraId) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Cartao> query = criteriaBuilder
				.createQuery(Cartao.class);
		Root<Cartao> root = query.from(Cartao.class);

		Path<Integer> clienteIdPath = root.join("cliente").<Integer> get("id");
		Path<Integer> bandeiraIdPath = root.join("bandeira").<Integer> get("id");
		Path<Integer> situacaoPath = root.join("situacaoRecebivel").<Integer> get("id");

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (clienteId != null) {
			Predicate clienteIdIgual = criteriaBuilder.equal(clienteIdPath, clienteId);
			predicates.add(clienteIdIgual);
		}

		if (bandeiraId != null) {
			Predicate bandeiraIdIgual = criteriaBuilder.equal(bandeiraIdPath, bandeiraId);
			predicates.add(bandeiraIdIgual);
		}
		
		Predicate situacaoIgual = criteriaBuilder.equal(situacaoPath, 1);
		predicates.add(situacaoIgual);

		query.where((Predicate[]) predicates.toArray(new Predicate[0]));

		TypedQuery<Cartao> typedQuery = manager.createQuery(query);
		typedQuery.setHint("org.hibernate.cacheable", "true");

		return typedQuery.getResultList();

	}
	
	
	
}
