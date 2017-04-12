package associazioni;

import costruzioni.Costruzione;
import costruzioni.CostruzioneElettrificata;

/**
 * Created by Tuly on 4/11/2017.
 */
public class Richiede {

    private CostruzioneElettrificata costruzione;
    private MattoncinoQuantity mattoncinoQuantity;

    public Richiede(CostruzioneElettrificata costruzione, MattoncinoQuantity mattoncinoQuantity) {
        this.costruzione = costruzione;
        this.mattoncinoQuantity = mattoncinoQuantity;
    }

    public CostruzioneElettrificata getCostruzione() {
        return costruzione;
    }

    public MattoncinoQuantity getMattoncinoQuantity() {
        return mattoncinoQuantity;
    }
}
