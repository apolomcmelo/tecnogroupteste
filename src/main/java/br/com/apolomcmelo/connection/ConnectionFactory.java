package br.com.apolomcmelo.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
	private static final String PERSISTENCE_UNIT = "pu_tecnogroup_teste";
	
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
	private static EntityManager entityManager = factory.createEntityManager();
	
	public static EntityManager getEntityManager() {
		return entityManager;
	}	
}