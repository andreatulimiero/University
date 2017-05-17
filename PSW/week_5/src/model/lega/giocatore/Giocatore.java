package model.lega.giocatore;

public class Giocatore {
    private final String nome;
    private final int annoNascita;

    public Giocatore(String nome, int annoNascita) {
        this.nome = nome;
        this.annoNascita = annoNascita;
    }

    public String getNome() {
        return nome;
    }

    public int getAnnoNascita() {
        return annoNascita;
    }
}
