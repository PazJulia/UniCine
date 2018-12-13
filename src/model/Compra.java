package model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Compra extends DefaultEntity<Compra>{

	private static final long serialVersionUID = 1800164396704812722L;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "compra")
	private List<Ingresso> listaIngresso;
	
	private TipoPagamento tipoPagamento;
	
	private Double valorTotalCompra;
	
	public Compra() {

	}

	public Compra(List<Ingresso> listaIngresso, TipoPagamento tipoPagamento,
			Double valorTotalCompra) {
		super();
		this.listaIngresso = listaIngresso;
		this.tipoPagamento = tipoPagamento;
		this.valorTotalCompra = valorTotalCompra;
	}

	public List<Ingresso> getListaIngresso() {
		return listaIngresso;
	}


	public void setListaIngresso(List<Ingresso> listaIngresso) {
		this.listaIngresso = listaIngresso;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}


	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}


	public Double getValorTotalCompra() {
		return valorTotalCompra;
	}

	public void setValorTotalCompra(Double valorTotalCompra) {
		this.valorTotalCompra = valorTotalCompra;
	}	
}
