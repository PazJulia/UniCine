package model;

public enum GeneroFilme {
	ACAO(0, "A��o"),
	ANIMACAO(1, "Anima��o"),
	AVENTURA(2, "Aventura"),
	COMEDIA(3, "Com�dia"),
	DRAMA(4, "Drama"),
	DOCUMENTARIO(5, "Document�rio"),
	FANTASIA(6, "Fantasia"),
	FICCAOCIENTIFICA(7, "Fic��o"),
	GUERRA(8, "Guerra"),
	MUSICAL(9, "Musical"),
	ROMANCE(10, "Romance"),
	SUSPENSE(11, "Suspense");
	
	private int id;
	private String label;
	
	private GeneroFilme(int id, String label)
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
