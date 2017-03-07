package officina;

public abstract class Mezzo {

    private final String marca;
    private final String modello;
    private final String colore;

    public Mezzo(String unaMarca, String unModello, String unColore) {
        marca = unaMarca;
        modello = unModello;
        colore = unColore;
    }

    public String getMarca() {
        return marca;
    }

    public String getModello() {
        return modello;
    }

    public String getColore() {
        return colore;
    }

    public abstract int getNumeroRuote();

}
