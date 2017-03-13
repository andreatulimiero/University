import java.util.*;

public class Operazioni {
	
	public static Utente migliorOfferente(Biglietto b) {
		Utente r=null;
		double maxprezzo=0;
		Iterator<Offerta> i = b.getOfferte().iterator();
		while (i.hasNext()) {
			Offerta o = i.next();
			double p = o.getPrezzo();
			if (p>maxprezzo) {
				r = o.getOfferente(); maxprezzo = p;
			}
		}
		return r;
	}

	public static boolean utenteSbadato(Utente u) {
		boolean found=false;
		Iterator<Biglietto> i1 = u.getBiglietti().iterator();
		while (i1.hasNext() && !found) {
			Biglietto b = i1.next();
			Iterator<Offerta> i2 = b.getOfferte().iterator();
			while (i2.hasNext() && !found) {
				Offerta o = i2.next();
				Utente uoff = o.getOfferente();
				found = found || u.equals(uoff); 
			}
		}
		return found;
	}
	
}
