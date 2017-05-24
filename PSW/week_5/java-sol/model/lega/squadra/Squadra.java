package model.lega.squadra;

import java.util.*;

import model.lega.EccezioneMolteplicita;
import model.lega.EccezioneSubset;
import model.lega.ManagerPartita;
import model.lega.TipoLinkPartita;
import model.lega.giocatore.Giocatore;

public class Squadra {
    private final int MOLT_MIN_GIOCA = 15;

    private final String nome;
    private HashSet<Giocatore> gioca;
    private Giocatore capitano;
    private HashSet<TipoLinkPartita> casa;
    private HashSet<TipoLinkPartita> trasferta;

    public Squadra(String nome) {
        casa = new HashSet<TipoLinkPartita>();
        trasferta = new HashSet<TipoLinkPartita>();
        gioca = new HashSet<Giocatore>();
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Set<Giocatore> getLinkGioca() throws EccezioneMolteplicita {
        if (gioca.size() < MOLT_MIN_GIOCA) {
            throw new EccezioneMolteplicita("Squadra " + nome + ": Molteplicita' minima violata!");
        }
        return (Set) gioca.clone();
    }

    public void insericiLinkGioca(Giocatore g) {
        gioca.add(g);
    }

    public void eliminaLinkGioca(Giocatore g) {
        gioca.remove(g);
    }

    public void setCapitano(Giocatore capitano) {
        this.capitano = capitano;
    }

    public Giocatore getCapitano() throws EccezioneMolteplicita, EccezioneSubset {
        if (capitano == null) {
            throw new EccezioneMolteplicita("Molteplicita' min/max violata!");
        }
        if (!gioca.contains(capitano)) {
            throw new EccezioneSubset("Vincolo di Subset violato!");
        }
        return capitano;
    }

    public Set<TipoLinkPartita> getLinkCasa() {
        return (Set<TipoLinkPartita>) casa.clone();
    }

    public Set<TipoLinkPartita> getLinkTrasferta() {
        return (Set<TipoLinkPartita>) trasferta.clone();
    }

    public void inserisciLinkPartita(TipoLinkPartita l) {
        if (l != null && l.getCasa() == this || l.getTrasferta() == this) {
            ManagerPartita.inserisci(l);
        }
    }

    public void eliminaLinkPartita(TipoLinkPartita l) {
        if (l != null && l.getCasa() == this || l.getTrasferta() == this) {
            ManagerPartita.elimina(l);
        }
    }

    public void inserisciCasaPerManagerPartita(ManagerPartita m) {
        if (m != null) {
            casa.add(m.getLink());
        }
    }

    public void inserisciTrasfertaPerManagerPartita(ManagerPartita m) {
        if (m != null) {
            trasferta.add(m.getLink());
        }
    }

    public void eliminaCasaPerManagerPartita(ManagerPartita m) {
        if (m != null) {
            casa.remove(m.getLink());
        }
    }

    public void eliminaTrasfertaPerManagerPartita(ManagerPartita m) {
        if (m != null) {
            trasferta.remove(m.getLink());
        }
    }
}
