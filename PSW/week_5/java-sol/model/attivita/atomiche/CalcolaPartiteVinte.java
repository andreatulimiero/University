package model.attivita.atomiche;

import model.lega.squadra.Squadra;
import model.lega.TipoLinkPartita;
import model._framework.Task;

import java.util.*;

public class CalcolaPartiteVinte implements Task {
    private int vinteCasa = 0;
    private int vinteTrasferta = 0;
    private TipoLinkPartita partita;
    private boolean eseguita = false;

    public CalcolaPartiteVinte(TipoLinkPartita partita) {
        if (partita == null) {
            throw (new RuntimeException("Il parametro non puo' essere null!"));
        }
        this.partita = partita;
    }

    public synchronized void esegui() {
        if (eseguita)
            return;
        eseguita = true;

        // Crea l'insieme delle partite giocate dalla squadra di casa e
        // vi aggiunge le partite che essa ha giocato in casa e fuori.

        // insieme vuoto
        Set<TipoLinkPartita> giocateDaSquadraCasa = new HashSet<TipoLinkPartita>();
        // unione con le partite giocate in casa
        giocateDaSquadraCasa.addAll(partita.getCasa().getLinkCasa());
        // unione con le partite giocate in trasferta
        giocateDaSquadraCasa.addAll(partita.getCasa().getLinkTrasferta());
        // Rimuove la partita fornita in input all'attivita'
        giocateDaSquadraCasa.remove(partita);

        // Calcola le partite vinte dalla squadra in casa
        vinteCasa = partiteVinte(partita.getCasa(), giocateDaSquadraCasa);

        // (Come sopra, per la squadra in trasferta)
        Set<TipoLinkPartita> giocateDaSquadraTrasferta = new HashSet<TipoLinkPartita>();
        giocateDaSquadraTrasferta.addAll(partita.getTrasferta().getLinkCasa());
        giocateDaSquadraTrasferta.addAll(partita.getTrasferta().getLinkTrasferta());
        giocateDaSquadraTrasferta.remove(partita);

        // Calcola le partite vinte dalla squadra in trasferta
        vinteTrasferta = partiteVinte(partita.getTrasferta(), giocateDaSquadraTrasferta);
    }

    private int partiteVinte(Squadra s, Set<TipoLinkPartita> partite) {
        /*Data una squadra s ed un insieme di partite, calcola quante delle ultime sono
		 * state vinte da s
		 * ATTENZIONE! Metodo privato (ausiliario): puo' essere eseguito solo dal Task, 
		 * che lo fara' all'interno di un blocco/metodo synchronized!
		*/
        int risultato = 0;
        Iterator<TipoLinkPartita> itp = partite.iterator();
        while (itp.hasNext()) {
            TipoLinkPartita partitaCorrente = itp.next();
            if ((//la squadra s ha giocato in casa e la squadra in casa ha vinto
                    partitaCorrente.getCasa() == s &&
                            (partitaCorrente.getGoalCasa() > partitaCorrente.getGoalTrasferta())
            )
                    ||
                    (//la squadra s ha giocato in trasferta e la squadra in trasferta ha vinto
                            partitaCorrente.getTrasferta() == s &&
                                    (partitaCorrente.getGoalTrasferta() > partitaCorrente.getGoalCasa())
                    )
                    ) {
                risultato++;
            }
        }

        return risultato;
    }

    public synchronized boolean estEseguita() {
        return eseguita;
    }

    public synchronized int getVinteCasa() {
        if (!eseguita)
            throw new RuntimeException("Risultato non disponibile!");
        return vinteCasa;
    }

    public synchronized int getVinteTrasferta() {
        if (!eseguita)
            throw new RuntimeException("Risultato non disponibile!");
        return vinteTrasferta;
    }
}
