package model.attivita.atomiche;

import model.lega.squadra.Squadra;
import model.lega.giocatore.Giocatore;
import model._framework.Task;

import java.util.*;

import model.lega.EccezioneMolteplicita;

public class CalcolaEtaMedia implements Task {
    // Task per il calcolo dell'eta' media dei giocatori di una squadra
    private final int ANNO_CORRENTE = 2010;
    private Squadra squadra;
    private boolean eseguita = false;
    private float risultato = 0;

    public CalcolaEtaMedia(Squadra squadra) {
        if (squadra == null) {
            throw (new RuntimeException("Il parametro non puo' essere null!"));
        }
        this.squadra = squadra;
    }

    public synchronized void esegui() {
        if (eseguita)
            return;
        eseguita = true;
        try {
            Set<Giocatore> giocatori = squadra.getLinkGioca();
            Iterator<Giocatore> it = giocatori.iterator();
            while (it.hasNext()) {
                risultato += (ANNO_CORRENTE - it.next().getAnnoNascita());
            }
            risultato = risultato / giocatori.size();
        } catch (EccezioneMolteplicita ecc) {
            ecc.printStackTrace();
        }
    }

    public synchronized boolean estEseguita() {
        return eseguita;
    }

    public synchronized float getRisultato() {
        if (!eseguita)
            throw new RuntimeException("Risultato non disponibile!");
        return risultato;
    }
}
