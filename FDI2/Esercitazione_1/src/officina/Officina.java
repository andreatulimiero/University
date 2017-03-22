package officina;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by tulim on 3/8/2017.
 */
public interface Officina {

    String partitaIva();

    void arrivaMezzo(Mezzo mezzo);

    void approntaMezzo(Mezzo mezzo);

    void parteMezzo(Mezzo mezzo);

    boolean estInRiparazione(Mezzo mezzo);

    boolean estPronto(Mezzo mezzo);

    ProtectedIterator<SideEffectOfficina.MezzoInOfficina> mezziInOfficina();

}
