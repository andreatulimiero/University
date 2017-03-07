package esercizioEsame;

public class Umanistica extends Facolta {
	
	private int numBiblio;

	public Umanistica(String n, int num) {
		super(n);
		this.numBiblio = num; 	
	}
	
	public int getNumeroBiblioteche(){
		return this.numBiblio;
	}

}
