package esercizioEsame;
import java.util.Iterator;


public class Operazioni {

	public static double numeroMedioStudenti(Docente d) {
		int temp = 0;
		int n = 0;
		double result = 0;
		
		Iterator<Corso> it = d.getCorsi().iterator();
		while ( it.hasNext() ) {
			n = n+1;
			temp = temp + it.next().getNumeroStudenti();
		}
		
		if (n != 0) result = temp / n;
		return result;
		
	}

	
}