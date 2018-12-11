package repository;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import factory.JPAFactory;
import model.Filme;
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

	
	
//	public List<Sessao> getListSessoes(){
//		Query query = getEntityManager().createQuery("SELECT s FROM Sessao s");
//		
//		List<Sessao> lista = query.getResultList();
//		
//		if(lista == null) {
//			lista = new ArrayList<Sessao>();
//		}
//		return lista;
//	}
	
	public List<Sessao> getListSessoes(Filme titulo){
		Query query = getEntityManager().createQuery("SELECT s FROM Sessao s");
//													+ "WHERE lower(c.titulo) like lower(:titulo)");
		
		List<Sessao> lista = query.getResultList();
		
		if(lista == null) {
			lista = new ArrayList<Sessao>();
		}
		return lista;
	}
	
	
//	public static void main(String[] args) {
//		SessaoRepository repo = new SessaoRepository(JPAFactory.getEntityManager());
//		System.out.println(repo.dataDoBanco());
//	}
	
	
	public boolean existeSessaoDisponivel(Integer idSala, LocalDate dia, Time horaInicial) {
		Query query = getEntityManager()
				.createQuery("SELECT "
						   + " s "
						   + "FROM " 
						   + "  Sessao s "
						   + "WHERE " 
						   + " s.dataExibicao = :dia AND " 
						   + " s.sala.id = :idSala AND " 
						   + " :horaInicial between s.horaInicio and s.horaTermino ");
		
		query.setParameter("dia", dia);
		query.setParameter("idSala", idSala);
		query.setParameter("horaInicial", horaInicial);
		
		List<Sessao> lista = query.getResultList();
		
		if(lista == null || lista.isEmpty()) 
			return true;
		
		return false;
	}
	
}
