package model.attivita.complesse;

import model.lega.TipoLinkPartita;
import model.attivita.atomiche.CalcolaPartiteVinte;
import model._framework.TaskExecutor;

public class SottoramoDx implements Runnable {
    private boolean eseguita = false;
    private int vinteCasa = 0;
    private int vinteTrasferta = 0;
    private TipoLinkPartita partita;

    public SottoramoDx(TipoLinkPartita partita) {
        this.partita = partita;
    }

    public synchronized void run() {
        if (eseguita)
            return;
        eseguita = true;

        CalcolaPartiteVinte cpv = new CalcolaPartiteVinte(partita);
        TaskExecutor.getInstance().perform(cpv);
        vinteCasa = cpv.getVinteCasa();
        vinteTrasferta = cpv.getVinteTrasferta();
    }

    public synchronized int getVinteCasa() {
        if (!eseguita)
            throw new RuntimeException("Il risultato non e' ancora pronto!");
        return vinteCasa;
    }

    public synchronized int getVinteTrasferta() {
        if (!eseguita)
            throw new RuntimeException("Il risultato non e' ancora pronto!");
        return vinteTrasferta;
    }

    public synchronized boolean estEseguita() {
        return eseguita;
    }
}
