package officina;

/**
 * Created by tulim on 3/8/2017.
 */
public class Auto extends Mezzo {

    private String targa;

    public Auto(String unaMarca, String unModello, String unColore, String targa) {
        super(unaMarca, unModello, unColore);
        this.targa = targa;
    }

    public String getTarga() {
        return targa;
    }

    @Override
    public int getNumeroRuote() {
        return 4;
    }
}
