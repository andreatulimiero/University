package esercizioEsame;

public class Scientifica extends Facolta {
	
	private int numLab;

	public Scientifica(String n, int num) {
		super(n);
		this.numLab = num;
	}
	
	public int getNumeroLaboratori(){
		return this.numLab;
	}

}
