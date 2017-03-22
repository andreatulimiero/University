package esercizioEsame;

import java.util.HashSet;
import java.util.Set;


public class Docente {
	
	private String nome;
	private String cognome;
	private Facolta myFac;
	private Set<Corso> corsi;

	public Docente(String n, String c) {
		this.nome = n;
		this.cognome = c;
		myFac = null;
		corsi = new HashSet<Corso>();
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getCognome() {
		return this.cognome;
	}
	
	public Facolta getFacolta() {
		return this.myFac;
	}
	
	public void setFacolta(Facolta fac) {
		this.myFac = fac;
	}
	
	public void addCorso(Corso c){
		this.corsi.add(c);
	}
	
	public Set<Corso> getCorsi() {
		return this.corsi;
	}

}
