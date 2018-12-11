package repository;

import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.DefaultEntity;

public class Repository <T extends DefaultEntity<? super T>> {
	
	private EntityManager entityManager;
	
	public Repository(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public T save(T entity) {
		return getEntityManager().merge(entity);
	}
	
	public void remove(T entity) {
		entity = getEntityManager().merge(entity);
		getEntityManager().remove(entity);
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public LocalDate dataDoBanco() {
		Query query = getEntityManager()
				.createNativeQuery("SELECT "
						   + " now() ");
		
		Timestamp data = (Timestamp) query.getSingleResult();
		
		return data.toLocalDateTime().toLocalDate();
	}
}
