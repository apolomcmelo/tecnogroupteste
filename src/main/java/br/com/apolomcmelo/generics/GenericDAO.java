package br.com.apolomcmelo.generics;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.apolomcmelo.connection.ConnectionFactory;

public class GenericDAO<T extends GenericEntity> {

	protected static EntityManager em = ConnectionFactory.getEntityManager();

	public void saveAll(List<T> list){
		try {
			em.getTransaction().begin();

			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					em.persist(list.get(i));
				}
			}
			
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}
}