import java.util.*;

public class Biglietto {
	private final String evento;
	private final String citta;
	private Set<Offerta> offerte;
	
	public Biglietto(String evento, String citta) {
		super();
		this.evento = evento;
		this.citta = citta;
		this.offerte = new HashSet<Offerta>();
	}

	public Set<Offerta> getOfferte() {
		return offerte; // memoria condivisa!!!
	}

	public void addOfferta(Offerta offerta) {
		this.offerte.add(offerta);
	}

	public String getEvento() {
		return evento;
	}

	public String getCitta() {
		return citta;
	}

	@Override
	public String toString() {
		return "Biglietto [evento=" + evento + ", citta=" + citta + ", offerte=" + offerte + "]";
	}

}
