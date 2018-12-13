package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ingresso extends DefaultEntity<Ingresso>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6768978133735000953L;
	
	@ManyToOne
	@JoinColumn(name="idCompra")
	private Compra compra;
	
	@ManyToOne
	@JoinColumn(name = "idSessao")
	private Sessao idSessao;
	
	private String poltrona;
	
	private Integer quantidade;	
	
	private TipoIngresso tipoIngresso;
	
	public Ingresso()
	{
		
	}
	
	public Ingresso(Compra compra, Sessao idSessao, String poltrona, Integer quantidade, TipoIngresso tipoIngresso) {
		super();
		this.compra = compra;
		this.idSessao = idSessao;
		this.poltrona = poltrona;
		this.quantidade = quantidade;
		this.tipoIngresso = tipoIngresso;
	}

	public Sessao getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(Sessao idSessao) {
		this.idSessao = idSessao;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public String getPoltrona() {
		return poltrona;
	}

	public void setPoltrona(String poltrona) {
		this.poltrona = poltrona;
	}

	public TipoIngresso getTipoIngresso() {
		return tipoIngresso;
	}

	public void setTipoIngresso(TipoIngresso tipoIngresso) {
		this.tipoIngresso = tipoIngresso;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
