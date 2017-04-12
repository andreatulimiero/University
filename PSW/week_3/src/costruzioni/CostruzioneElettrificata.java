package costruzioni;

import associazioni.MattoncinoQuantity;
import associazioni.Richiede;
import associazioni.RichiedeManager;

import java.util.ArrayList;

/**
 * Created by Tuly on 4/11/2017.
 */
public class CostruzioneElettrificata extends Costruzione {

    private MattoncinoQuantity mattoncinoQuantity;

    public CostruzioneElettrificata(String instructions) {
        super(instructions);
    }

    public void managerAddRichiede(RichiedeManager richiedeManager) {
        Richiede richiede = richiedeManager.getRichiede();
        if (!richiede.getCostruzione().equals(this)) return;
        mattoncinoQuantity = new MattoncinoQuantity(
                richiede.getMattoncinoQuantity().getMattoncino(),
                richiede.getMattoncinoQuantity().getQuantity()
        );
    }

    public void managerRemoveRichiede(RichiedeManager richiedeManager) {
        Richiede richiede = richiedeManager.getRichiede();
        if (!richiede.getCostruzione().equals(this)) return;
        if (!richiede.getMattoncinoQuantity().getMattoncino().equals(mattoncinoQuantity.getMattoncino())) return;
        mattoncinoQuantity = null;
    }
}
