package model;

import java.sql.Time;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Sessao extends DefaultEntity<Sessao>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5847923521322507317L;
	
	@ManyToOne
	@JoinColumn(name = "idFilme")
	private Filme filme;
	
	@OneToOne
	@JoinColumn(name = "idSala")
	private Sala sala;
	
	@Column(columnDefinition="Date")
	private LocalDate dataExibicao;
	
	private Time horaInicio;
	
	private Time horaTermino;
	

	public LocalDate getDataExibicao() {
		return dataExibicao;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Time getHoraTermino() {
		return horaTermino;
	}

	public void setHoraTermino(Time horaTermino) {
		this.horaTermino = horaTermino;
	}

	public void setDataExibicao(LocalDate dataExibicao) {
		this.dataExibicao = dataExibicao;
	}
	
	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
}
