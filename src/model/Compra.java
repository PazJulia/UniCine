package model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Compra extends DefaultEntity<Compra>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1800164396704812722L;
	

	@ManyToOne
	@JoinColumn(name = "idSessao")
	private Sessao idSessao;
	private TipoIngresso tipoIngresso;
	private int poltrona = 50;
	
	
	public Sessao getIdSessao() {
		return idSessao;
	}
	public void setIdSessao(Sessao idSessao) {
		this.idSessao = idSessao;
	}
	public TipoIngresso getTipoIngresso() {
		return tipoIngresso;
	}
	public void setTipoIngresso(TipoIngresso tipoIngresso) {
		this.tipoIngresso = tipoIngresso;
	}
	public int getPoltrona() {
		return poltrona;
	}
	public void setPoltrona(int poltrona) {
		this.poltrona = poltrona;
	}
	
}
