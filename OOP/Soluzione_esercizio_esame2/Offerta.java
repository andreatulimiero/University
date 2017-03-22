
public class Offerta {
	private final Utente offerente;
	private final double prezzo;
	
	public Offerta(Utente offerente, double prezzo) {
		super();
		this.offerente = offerente;
		this.prezzo = prezzo;
	}

	public Utente getOfferente() {
		return offerente;
	}

	public double getPrezzo() {
		return prezzo;
	}

	@Override
	public String toString() {
		return "Offerta [offerente=" + offerente + ", prezzo=" + prezzo + "]";
	}
	
}
