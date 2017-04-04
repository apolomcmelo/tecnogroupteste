package br.com.apolomcmelo.daos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.apolomcmelo.generics.GenericDAO;
import br.com.apolomcmelo.models.Pedido;

public class ProdutoDAO extends GenericDAO<Pedido> {

	/**
	 * Retorna uma lista de Pedidos baseada nos parâmetros informados
	 * @param numeroControle
	 * @param dataCadastro
	 * @return
	 */
	public List<Pedido> findByParams(Long numeroControle, Date dataCadastro) {
	    CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Pedido> cq = cb.createQuery(Pedido.class);
	    Root<Pedido> root = cq.from(Pedido.class);
	   
	    List<Predicate> params = new ArrayList<Predicate>();
	    
	    if(numeroControle != null) {
	    	params.add(cb.equal(root.get("numeroControle"), numeroControle));	    	
	    }

	    if(dataCadastro != null) {
			params.add(cb.equal(root.get("dataCadastro"), dataCadastro));
	    }
	    
	    cq.select(root).where(params.toArray(new Predicate[]{}));

	    return em.createQuery(cq).getResultList();
	}
}