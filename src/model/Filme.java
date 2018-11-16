package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Filme extends DefaultEntity<Filme> {

	private static final long serialVersionUID = -6236478959721247796L;
	private String titulo;
	private String diretor;
	private String duracao;
	private String elenco;
	private GeneroFilme genero;
	private String produtora;
	private int classificacao;
	private String descricao;

	@Column(columnDefinition = "Date")
	private LocalDate dataInicio;

	@Column(columnDefinition = "Date")
	private LocalDate dataTermino;

	public Filme() {

	}

	public Filme(String titulo, String diretor, String duracao, String elenco, GeneroFilme genero, String produtora,
			int classificacao, String descricao, LocalDate dataInicio, LocalDate dataTermino) {
		this.titulo = titulo;
		this.diretor = diretor;
		this.duracao = duracao;
		this.elenco = elenco;
		this.genero = genero;
		this.produtora = produtora;
		this.classificacao = classificacao;
		this.descricao = descricao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public String getElenco() {
		return elenco;
	}

	public void setElenco(String elenco) {
		this.elenco = elenco;
	}

	public GeneroFilme getGenero() {
		return genero;
	}

	public void setGenero(GeneroFilme genero) {
		this.genero = genero;
	}

	public String getProdutora() {
		return produtora;
	}

	public void setProdutora(String produtora) {
		this.produtora = produtora;
	}

	public int getClassificacao() {
		return classificacao;
	}
	
	public String getTextClassificacao() {
		if (classificacao == 0)
			return "Livre";
		else if(classificacao == 1)
			return "10";
		else if(classificacao == 2)
			return "12";
		else if(classificacao == 3)
			return "14";
		else if(classificacao == 4)
			return "16";
		else if(classificacao == 5)
			return "18";
		
		return " ";
	}

	public void setClassificacao(int classificacao) {
		this.classificacao = classificacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(LocalDate dataTermino) {
		this.dataTermino = dataTermino;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	
    public String toString() {
        return this.getTitulo();
    }
}
