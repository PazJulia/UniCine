package model;

public enum Perfil {
	ADMINISTRADOR(0, "Administrador"),
	VENDEDOR(1, "Vendedor");
	
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
