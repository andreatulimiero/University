package associazioni;

import costruzioni.Costruzione;
import costruzioni.CostruzioneElettrificata;
import mattoncini.MattoncinoElettrificato;

/**
 * Created by Tuly on 4/11/2017.
 */
public class RichiedeManager {

    private Richiede richiede;

    private RichiedeManager(Richiede richiede) {
        this.richiede = richiede;
    }

    public Richiede getRichiede() {
        return richiede;
    }

    public void addRichiede(CostruzioneElettrificata ce, MattoncinoElettrificato me, int quantity) {
        MattoncinoQuantity mattoncinoQuantity = new MattoncinoQuantity(me, quantity);
        Richiede richiede = new Richiede(ce, mattoncinoQuantity);
        RichiedeManager richiedeManager = new RichiedeManager(richiede);
        ce.managerAddRichiede(richiedeManager);
        me.managerAddRichiede(richiedeManager);
    }

    public void removeRichiede(CostruzioneElettrificata ce, MattoncinoElettrificato me) {
        Richiede richiede = new Richiede(ce, new MattoncinoQuantity(me, 0));
        RichiedeManager richiedeManager = new RichiedeManager(richiede);
        ce.managerRemoveRichiede(richiedeManager);
        me.managerRemoveRichiede(richiedeManager);
    }
}
