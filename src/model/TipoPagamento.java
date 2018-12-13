package model;

public enum TipoPagamento {
	DEBITO(0, "Debito"),
	CREDITO(1, "Credito"),
	DINHEIRO(2, "Dinheiro");
	
	private int id;
	private String label;
	
	private TipoPagamento(int id, String label)
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
