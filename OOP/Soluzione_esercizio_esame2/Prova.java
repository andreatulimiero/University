import java.util.*;

public class Prova {

	static Set<Utente> dbutenti = new HashSet<Utente>();

	public static Utente generateUtente1() {
		return new Utente("Mario","Rossi","Roma");
	}
	
	public static Utente generateUtente2() {
		return new Utente("Maria","Bianchi","Roma");
	}

	public static Utente generateUtente3() {
		return new Utente("Carla","Verdi","Roma");
	}

	public static void generateData() {
		Utente u1 = generateUtente1();
		Utente u2 = generateUtente2();
		Utente u3 = generateUtente3();
		
		dbutenti.add(u1); dbutenti.add(u2); dbutenti.add(u3);
		
		Biglietto b1 = new Biglietto("evento1","Roma");
		u1.addBiglietto(b1); 
		
		Offerta o11 = new Offerta(u1,11.0);
		Offerta o12 = new Offerta(u2,10.0);
		
		b1.addOfferta(o11); b1.addOfferta(o12);
		
		Biglietto b2 = new Biglietto("evento2","Roma");
		u2.addBiglietto(b2);
		
		Offerta o21 = new Offerta(u2,11.0);
		Offerta o22 = new Offerta(u3,12.0);
		Offerta o23 = new Offerta(u3,14.0);
		
		b2.addOfferta(o21); b2.addOfferta(o22); b2.addOfferta(o23);
		
		Biglietto b3 = new Biglietto("evento3","Roma");
		u3.addBiglietto(b3);
		
		Offerta o31 = new Offerta(u1,9.0);
		
		b3.addOfferta(o31);
		
	}
	
	static Utente scegliUtente() {
		return dbutenti.iterator().next();
	}
	
	static Biglietto scegliBiglietto() {
		return scegliUtente().getBiglietti().iterator().next();
	}
	
	public static void main(String[] args) {
		generateData();
		
		Biglietto b1 = scegliBiglietto();
		Utente u = Operazioni.migliorOfferente(b1);
		System.out.println("===========================================");
		System.out.println("Miglior offerente per biglietto "+b1);
		System.out.println("Utente: "+u);
		
		Utente u1 = scegliUtente();
		boolean r = Operazioni.utenteSbadato(u1);
		System.out.println("===========================================");
		System.out.println("Verifica acquisto proprio biglietto per utente: "+u1);
		System.out.println(r);

	}

}
