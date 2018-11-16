package model;

public enum TipoSala {

	D2(0, "2D"),
	D3(1, "3D");
	
	private int id;
	private String label;
	
	private TipoSala(int id, String label)
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
