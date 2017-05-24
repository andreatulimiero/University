package model.attivita.complesse;

import model.lega.TipoLinkPartita;
import model.attivita.atomiche.SegnaliIO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class AttivitaPrincipale implements Runnable {

    private boolean eseguita = false;

    private SegnaliIO segnaliIO;
    private Set<TipoLinkPartita> partite;

    public AttivitaPrincipale(SegnaliIO segnaliIO, Set<TipoLinkPartita> partite) {
        this.segnaliIO = segnaliIO;
        this.partite = partite;
    }

    public synchronized void run() {

        if (eseguita)
            return;
        eseguita = true;

        TipoLinkPartita partitaSelezionata = null;

        do {

            LinkedList<String> list = new LinkedList<String>();
            HashMap<Integer, TipoLinkPartita> map = new HashMap<Integer, TipoLinkPartita>();

            int i = 0;
            for (TipoLinkPartita tlp : partite) {
                map.put(i++, tlp);
                list.add(tlp.getCasa().getNome() + "-" + tlp.getTrasferta().getNome()
                        + " : " + tlp.getGoalCasa() + " - " + tlp.getGoalTrasferta());
            }

            int index = segnaliIO.selezionaPartita(list);
            partitaSelezionata = map.get(index);

            if (partitaSelezionata != null) {
                SottoramoSx sottoramoSx = new SottoramoSx(partitaSelezionata, segnaliIO);
                SottoramoDx sottoramoDx = new SottoramoDx(partitaSelezionata);
                Thread t1 = new Thread(sottoramoSx);
                Thread t2 = new Thread(sottoramoDx);
                t1.start();
                t2.start();
                try {
                    t1.join();
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                segnaliIO.stampa(sottoramoDx.getVinteCasa(),
                        sottoramoDx.getVinteTrasferta(),
                        sottoramoSx.getMediaEtaCasa(),
                        sottoramoSx.getMediaEtaTrasferta());
            }
        }
        while (partitaSelezionata != null);
    }

    synchronized boolean estEseguita() {
        return eseguita;
    }

}
