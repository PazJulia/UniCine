package model;

public enum GeneroFilme {
	ACAO(0, "Ação"),
	ANIMACAO(1, "Animação"),
	AVENTURA(2, "Aventura"),
	COMEDIA(3, "Comédia"),
	DRAMA(4, "Drama"),
	DOCUMENTARIO(5, "Documentário"),
	FANTASIA(6, "Fantasia"),
	FICCAOCIENTIFICA(7, "Ficção"),
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
