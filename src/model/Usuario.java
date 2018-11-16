package model;

import javax.persistence.Entity;

@Entity
public class Usuario extends DefaultEntity<Usuario>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6088560814082665700L;
	
	private String nome;
	private String login;
	private String senha;
	private Perfil perfil;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
}
