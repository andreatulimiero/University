package mattoncini;

import associazioni.Richiede;
import associazioni.RichiedeManager;
import costruzioni.CostruzioneElettrificata;

import java.util.HashSet;

/**
 * Created by Tuly on 4/11/2017.
 */
public class MattoncinoElettrificato extends Mattoncino {

    private String specification;
    private HashSet<CostruzioneElettrificata> costruzioniElettrificateSet;

    public MattoncinoElettrificato(String dimension, String color, String specification) {
        super(dimension, color);

        this.specification = specification;
    }

    public String getSpecification() {
        return specification;
    }

    public void managerAddRichiede(RichiedeManager richiedeManager) {
        Richiede richiede = richiedeManager.getRichiede();
        if (!richiede.getMattoncinoQuantity().getMattoncino().equals(this)) return;
        costruzioniElettrificateSet.add(richiede.getCostruzione());
    }

    public void managerRemoveRichiede(RichiedeManager richiedeManager) {
        Richiede richiede = richiedeManager.getRichiede();
        if (!richiede.getMattoncinoQuantity().getMattoncino().equals(this)) return;
        costruzioniElettrificateSet.remove(richiede.getCostruzione());
    }
}
