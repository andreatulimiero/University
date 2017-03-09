package officina;

/**
 * Created by tulim on 3/8/2017.
 */
public class Camion extends Mezzo {

    private boolean hasRimorchio;

    public Camion(String unaMarca, String unModello, String unColore, boolean hasRimorchio) {
        super(unaMarca, unModello, unColore);
        this.hasRimorchio = hasRimorchio;
    }

    public boolean hasRimorchio() {
        return hasRimorchio;
    }

    @Override
    public int getNumeroRuote() {
        return hasRimorchio ? 8 : 4;
    }

}
