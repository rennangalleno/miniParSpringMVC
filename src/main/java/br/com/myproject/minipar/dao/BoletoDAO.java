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

import br.com.myproject.minipar.models.Boleto;

@Repository @Transactional
public class BoletoDAO {

	@PersistenceContext
	private EntityManager manager;

	public void gravar(Boleto boleto) {
		if(boleto.getId() == null) {
			manager.persist(boleto);
		} else {
			manager.merge(boleto);
		}
	}

	public List<Boleto> listar(){
		return manager.createQuery("select b from Boleto b "
				+ "join fetch b.pagador "
				+ "join fetch b.cliente "
				+ "join fetch b.tipoRecebivel "
				+ "join fetch b.situacaoRecebivel "
				+ "where b.situacaoRecebivel = 1", Boleto.class).getResultList();	
	}

	public Boleto find(Integer id) {
		return manager.find(Boleto.class, id);
	}

	public List<Boleto> consultar(Integer clienteId, Integer pagadorId) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Boleto> query = criteriaBuilder
				.createQuery(Boleto.class);
		Root<Boleto> root = query.from(Boleto.class);

		Path<Integer> clienteIdPath = root.join("cliente").<Integer> get("id");
		Path<Integer> pagadorIdPath = root.join("pagador").<Integer> get("id");
		Path<Integer> situacaoPath = root.join("situacaoRecebivel").<Integer> get("id");

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (clienteId != null) {
			Predicate clienteIdIgual = criteriaBuilder.equal(clienteIdPath, clienteId);
			predicates.add(clienteIdIgual);
		}

		if (pagadorId != null) {
			Predicate pagadorIdIgual = criteriaBuilder.equal(pagadorIdPath, pagadorId);
			predicates.add(pagadorIdIgual);
		}
		
		Predicate situacaoIgual = criteriaBuilder.equal(situacaoPath, 1);
		predicates.add(situacaoIgual);

		query.where((Predicate[]) predicates.toArray(new Predicate[0]));

		TypedQuery<Boleto> typedQuery = manager.createQuery(query);
		typedQuery.setHint("org.hibernate.cacheable", "true");

		return typedQuery.getResultList();

	}
}
