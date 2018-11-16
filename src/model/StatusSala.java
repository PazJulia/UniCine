package model;

public enum StatusSala {
	DISPONIVEL(0, "Disponível"),
	INDISPONIVEL(1, "Indisponível");
	
	private int id;
	private String label;
	
	private StatusSala(int id, String label)
	{
		this.id = id;
		this.label = label;
	}
	
	public int getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}
	
	@Override
	public String toString()
	{
		return label;
	}
}
