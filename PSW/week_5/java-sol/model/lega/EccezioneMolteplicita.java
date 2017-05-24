package model.lega;

public class EccezioneMolteplicita extends Exception {
    private String messaggio;

    public EccezioneMolteplicita(String messaggio) {
        this.messaggio = messaggio;
    }

    public String toString() {
        return messaggio;
    }
}
