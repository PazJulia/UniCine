package repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Filme;
import model.Sala;

public class SalaRepository extends Repository<Sala>{

	public SalaRepository(EntityManager entityManager) {
		super(entityManager);
	}
	
	public List<Sala> getSalas(String nomeSala) {

		Query query = getEntityManager()
				.createQuery("SELECT c FROM Sala c WHERE lower(c.nomeSala) like lower(:nomeSala) ");
		query.setParameter("nomeSala", "%" + nomeSala + "%");

		List<Sala> lista = query.getResultList();

		if (lista == null)
			lista = new ArrayList<Sala>();

		return lista;
	}
	
	public List<Sala> getListNomesSalas(){
		Query query = getEntityManager().createQuery("SELECT c FROM Sala c");
		
		List<Sala> lista = query.getResultList();
		
		if(lista == null) {
			lista = new ArrayList<Sala>();
		}
		return lista;
	}

}
