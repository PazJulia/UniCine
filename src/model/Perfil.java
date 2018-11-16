package model;

public enum Perfil {
	ADMINISTRADOR(0, "Adminstrador"),
	CADASTRO(1, "Cadastro");
	
	private int id;
	private String label;
	
	private Perfil(int id, String label)
	{
		this.id = id;
		this.label = label;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getLabel()
	{
		return label;
	}
}
