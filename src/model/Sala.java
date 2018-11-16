package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Sala extends DefaultEntity<Sala>{

	private static final long serialVersionUID = 5285883475948986123L;

	private String nomeSala;
	
	private StatusSala status;
	
	private TipoSala tipoSala;
	
	public Sala()
	{
		
	}
	
	public Sala(String nomeSala, TipoSala tipoSala)
	{
		this.nomeSala = nomeSala;
		this.tipoSala = tipoSala;
	}

	public String getNomeSala() {
		return nomeSala;
	}

	public void setNomeSala(String nomeSala) {
		this.nomeSala = nomeSala;
	}

	public TipoSala getTipoSala() {
		return tipoSala;
	}

	public void setTipoSala(TipoSala tipoSala) {
		this.tipoSala = tipoSala;
	}

	public StatusSala getStatus() {
		return status;
	}

	public void setStatus(StatusSala status) {
		this.status = status;
	}

    public String toString() {
        return this.getNomeSala();
    }
	
}
