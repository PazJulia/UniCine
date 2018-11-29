package repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Filme;
import model.Sala;
import model.Sessao;

public class SessaoRepository extends Repository<Sessao>{

	public SessaoRepository(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}
	
//	public List<Sessao> getSessao(String id) {
//
//		Query query = getEntityManager()
//				.createQuery("SELECT c FROM Sessao c WHERE lower(c.id) like lower(:id) ");
//		query.setParameter("id", "%" + id + "%");
//
//		List<Sessao> lista = query.getResultList();
//
//		if (lista == null)
//			lista = new ArrayList<Sessao>();
//
//		return lista;
//	}
	
	public List<Sessao> getListSessoes(){
		Query query = getEntityManager().createQuery("SELECT c FROM Sessao c");
		
		List<Sessao> lista = query.getResultList();
		
		if(lista == null) {
			lista = new ArrayList<Sessao>();
		}
		return lista;
	}
}
