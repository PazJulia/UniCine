package repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Filme;

public class FilmeRepository extends Repository<Filme> {

	public FilmeRepository(EntityManager entityManager) {
		super(entityManager);
	}

	public List<Filme> getFilmes(String titulo) {

		Query query = getEntityManager()
				.createQuery("SELECT c FROM Filme c WHERE lower(c.titulo) like lower(:titulo) ");
		query.setParameter("titulo", "%" + titulo + "%");

		List<Filme> lista = query.getResultList();

		if (lista == null)
			lista = new ArrayList<Filme>();

		return lista;
	}
	
	public List<Filme> getListNomesFilmes(){
		Query query = getEntityManager().createQuery("SELECT c FROM Filme c");
		
		List<Filme> lista = query.getResultList();
		
		if(lista == null) {
			lista = new ArrayList<Filme>();
		}
		return lista;
	}
}
