package esercizioEsame;

public class Corso {
	private String nome;
	private int numStud;
	
	public Corso(String nome, int num) {
		this.nome = nome;
		this.numStud = num;
	}

	public String getNome() {
		return this.nome;
	}
	public int getNumeroStudenti() {
		return this.numStud;
	}
}
