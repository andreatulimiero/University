package model.lega;

public class EccezioneSubset extends Exception {
    private String messaggio;

    public EccezioneSubset(String messaggio) {
        this.messaggio = messaggio;
    }

    public String toString() {
        return (messaggio);
    }
}
