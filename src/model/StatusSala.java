package model;

public enum StatusSala {
	DISPONIVEL(0, "Dispon�vel"),
	INDISPONIVEL(1, "Indispon�vel");
	
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
