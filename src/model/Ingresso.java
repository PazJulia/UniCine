package model;

import java.sql.Time;
import java.time.LocalDate;

public class Ingresso extends DefaultEntity<Ingresso>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6768978133735000953L;
	
	private Filme filme;
	
	private Sala sala;
	
	private Time horaInicio;
	
	private String poltrona;
	
	private LocalDate dataExibicao;
	
	private LocalDate dataCompra;
	
	private double valor;
	
	
}
