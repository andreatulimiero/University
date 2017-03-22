import java.util.*;

public class Utente {
	private final String nome, cognome;
	private String residenza;
	private Set<Biglietto> biglietti;
	
	public Utente(String nome, String cognome, String residenza) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.residenza = residenza;
		this.biglietti = new HashSet<Biglietto>();
	}

	public String getResidenza() {
		return residenza;
	}

	public void setResidenza(String residenza) {
		this.residenza = residenza;
	}

	public Set<Biglietto> getBiglietti() {
		return biglietti; // memoria condivisa!!!
	}

	public void addBiglietto(Biglietto b) {
		this.biglietti.add(b);
	}

	public void removeBiglietto(Biglietto b) {
		this.biglietti.remove(b); // uguaglianza???
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", cognome=" + cognome + ", residenza=" + residenza + "]";
	}

}
