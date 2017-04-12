package costruzioni;

import associazioni.MattoncinoQuantity;

import java.util.HashSet;

/**
 * Created by Tuly on 4/11/2017.
 */
public class CostruzioneSemplice extends Costruzione {

    private HashSet<MattoncinoQuantity> mattoncinoQuantitySet;

    public CostruzioneSemplice(String instructions) {
        super(instructions);

        mattoncinoQuantitySet = new HashSet<>();
    }

    public void addMattoncinoQuanity(MattoncinoQuantity mattoncinoQuantity) {
        if (mattoncinoQuantity == null) return;
        mattoncinoQuantitySet.add(mattoncinoQuantity);
    }

    public void removeMattoncinoQuantity(MattoncinoQuantity mattoncinoQuantity) {
        if (mattoncinoQuantity == null) return;
        mattoncinoQuantitySet.remove(mattoncinoQuantity);
    }

    public HashSet<MattoncinoQuantity> getMattoncinoQuantitySet() {
        return (HashSet<MattoncinoQuantity>) mattoncinoQuantitySet.clone();
    }
}
