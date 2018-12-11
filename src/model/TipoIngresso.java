package model;

public enum TipoIngresso {
	INTEIRA(0, "Inteira"),
	MEIA(1, "Meia");
	
	private int id;
	private String label;
	
	private TipoIngresso(int id, String label)
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
